package org.example.repositorio;

import org.example.excepciones.ExcepcionRepositorio;
import org.example.modelo.Actor;
import org.example.modelo.Pelicula;
import org.example.servicio.IRepositorio;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositorioPostgreSql implements IRepositorio {

    private Connection connection;

    public RepositorioPostgreSql() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

            String url = props.getProperty("db.pg.url");
            String user = props.getProperty("db.pg.user");
            String pass = props.getProperty("db.pg.pass");

            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión PostgreSQL OK");

        } catch (Exception e) {
            throw new RuntimeException("Error conectando a PostgreSQL: " + e.getMessage(), e);
        }
    }

    // INSERTAR
    public void guardar(Pelicula p) {
        String sqlAuto = "INSERT INTO peliculas (titulo, duracion) VALUES (?, ?)";
        String sqlManual = "INSERT INTO peliculas (id, titulo, duracion) VALUES (?, ?, ?)";
        String sqlAct = "INSERT INTO actores (id_pelicula, nombre, edad, personaje) VALUES (?, ?, ?, ?) RETURNING id";

        PreparedStatement stmt = null;
        PreparedStatement stmtAct = null;
        ResultSet rsKeys = null;
        ResultSet rsAct = null;

        try {
            connection.setAutoCommit(false); // inicio de transacción

            long idPelicula = p.getId();

            //Insertar película
            if (idPelicula <= 0) {
                stmt = connection.prepareStatement(sqlAuto, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, p.getTitulo());
                stmt.setInt(2, p.getDuracion());
                stmt.executeUpdate();

                rsKeys = stmt.getGeneratedKeys();
                if (rsKeys.next()) {
                    idPelicula = rsKeys.getInt(1);
                }
            } else {
                stmt = connection.prepareStatement(sqlManual);
                stmt.setLong(1, idPelicula);
                stmt.setString(2, p.getTitulo());
                stmt.setInt(3, p.getDuracion());
                stmt.executeUpdate();
            }


            // Insertar actores
            for (Actor a : p.getListaActores()) {
                stmtAct = connection.prepareStatement(sqlAct);
                stmtAct.setLong(1, idPelicula);
                stmtAct.setString(2, a.getNombre());
                stmtAct.setInt(3, a.getEdad());
                stmtAct.setString(4, a.getPersonaje());
                System.out.println("no llega aqui");

                rsAct = stmtAct.executeQuery(); // executeQuery porque usamos RETURNING
                if (rsAct.next()) {
                    a.setId(rsAct.getInt(1)); // guardamos el ID generado
                }
                // Cerramos el ResultSet del actor para reutilizar stmtAct
                rsAct.close();

                stmtAct.close();
            }

            connection.commit(); // confirmamos transacción
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new ExcepcionRepositorio("Error la recuperar datos", e);
            }
            throw new ExcepcionRepositorio("Error insertando película: ", e);
        } finally {
            try {
                if (rsKeys != null) {
                    rsKeys.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                connection.setAutoCommit(true); // aseguramos volver a true
            } catch (SQLException e) {
                throw new ExcepcionRepositorio("Error cerrando recursos: ", e);
            }
        }
    }

    // LISTAR
    public List<Pelicula> listar() {
        List<Pelicula> lista = new ArrayList<>();

        String sqlPel = "SELECT id, titulo, duracion FROM peliculas";
        String sqlAct = "SELECT nombre, edad, personaje FROM actores WHERE id_pelicula = ?";
        List<Actor> actores;
        int id;
        String titulo;
        int duracion;

        try  {
            Statement stmtPel = connection.createStatement();
            ResultSet rsPel = stmtPel.executeQuery(sqlPel);

            while (rsPel.next()) {
                 id = rsPel.getInt("id");
                 titulo = rsPel.getString("titulo");
                 duracion = rsPel.getInt("duracion");

                actores = new ArrayList<>();
                try (PreparedStatement stmtAct = connection.prepareStatement(sqlAct)) {
                    stmtAct.setInt(1, id);
                    try (ResultSet rsAct = stmtAct.executeQuery()) {
                        while (rsAct.next()) {
                            actores.add(new Actor(
                                    0, // id de actor si no lo usamos
                                    rsAct.getString("nombre"),
                                    rsAct.getInt("edad"),
                                    rsAct.getString("personaje")
                            ));
                        }
                    }
                }

                lista.add(new Pelicula(id, titulo, duracion, actores));
            }

        } catch (SQLException e) {
            throw new ExcepcionRepositorio("Error listando películas con actores", e);
        }

        return lista;
    }

    // ACTUALIZAR
    public void actualizar(Pelicula p) {
            String sqlPel = "UPDATE peliculas SET titulo = ?, duracion = ? WHERE id = ?";
            String sqlDelAct = "DELETE FROM actores WHERE id_pelicula = ?";
            String sqlInsAct = "INSERT INTO actores (id_pelicula, nombre, edad, personaje) VALUES (?, ?, ?, ?)";

            try {
                connection.setAutoCommit(false); // inicio de transacción

                //Actualizar película
                try (PreparedStatement stmtPel = connection.prepareStatement(sqlPel)) {
                    stmtPel.setString(1, p.getTitulo());
                    stmtPel.setInt(2, p.getDuracion());
                    stmtPel.setLong(3, p.getId());
                    stmtPel.executeUpdate();
                }

                //Borrar actores antiguos
                try (PreparedStatement stmtDel = connection.prepareStatement(sqlDelAct)) {
                    stmtDel.setLong(1, p.getId());
                    stmtDel.executeUpdate();
                }

                //Insertar actores nuevos
                try (PreparedStatement stmtIns = connection.prepareStatement(sqlInsAct)) {
                    for (Actor a : p.getListaActores()) {
                        stmtIns.setLong(1, p.getId());
                        stmtIns.setString(2, a.getNombre());
                        stmtIns.setInt(3, a.getEdad());
                        stmtIns.setString(4, a.getPersonaje());
                        stmtIns.addBatch();
                    }
                    stmtIns.executeBatch();
                }

                connection.commit(); // confirmamos transacción
                connection.setAutoCommit(true);

            } catch (SQLException e) {
                try { connection.rollback(); } catch (SQLException ex) {}
                throw new ExcepcionRepositorio("Error actualizando película con actores", e);
            }
        }


    // BORRAR
    public void borrar(int id) {
        String sql = "DELETE FROM peliculas WHERE id = ?";

        try  {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExcepcionRepositorio("Error borrando película: ", e);
        }
    }
}
