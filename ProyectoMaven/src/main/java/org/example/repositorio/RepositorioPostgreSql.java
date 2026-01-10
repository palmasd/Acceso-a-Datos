package org.example.repositorio;

import org.example.modelo.Pelicula;
import org.example.servicio.IRepositorio;

import java.sql.*;
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

        } catch (Exception e) {
            System.out.println("Error conectando a PostgreSQL: " + e.getMessage());
        }
    }

    // INSERTAR
    public void guardar(Pelicula p) {
        String sql = "INSERT INTO peliculas (id, titulo, genero, minutos) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, p.getId());
            stmt.setString(2, p.getTitulo());
            stmt.setInt(3, p.getDuracion());
            //stmt.setInt(4, p.getMinutos());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insertando película: " + e.getMessage());
        }
    }

    // LISTAR
    public List<Pelicula> listar() {
        List<Pelicula> lista = new ArrayList<>();
//        String sql = "SELECT * FROM peliculas";
//
//        try (Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                lista.add(new Pelicula(
//                        rs.getInt("id"),
//                        rs.getString("titulo"),
//                        rs.getInt("duracion"),
//                        rs.get("minutos")
//                ));
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error listando películas: " + e.getMessage());
//        }
//
       return lista;
    }

    // ACTUALIZAR
    public void actualizar(Pelicula p) {
        String sql = "UPDATE peliculas SET titulo = ?, genero = ?, minutos = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, p.getTitulo());
            stmt.setInt(2, p.getDuracion());
            //stmt.setInt(3, p.getMinutos());
            stmt.setLong(4, p.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando película: " + e.getMessage());
        }
    }

    // BORRAR
    public void borrar(int id) {
        String sql = "DELETE FROM peliculas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error borrando película: " + e.getMessage());
        }
    }

}
