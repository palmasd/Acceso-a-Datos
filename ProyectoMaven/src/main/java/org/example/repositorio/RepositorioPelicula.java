package org.example.repositorio;

import org.example.excepciones.ExcepcionRepositorio;
import org.example.modelo.Actor;
import org.example.modelo.Pelicula;
import org.example.servicio.IRepositorio;
import org.example.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPelicula implements IRepositorio {

    private final Connection conexion;

    public RepositorioPelicula() {
        this.conexion = DBConnection.getConnection();
    }

    public void guardar(Pelicula p) {

        String sqlPel = "INSERT INTO pelicula (titulo, duracion) VALUES (?, ?)";
        String sqlAct = "INSERT INTO actor (id_pelicula, nombre, edad, personaje) VALUES (?, ?, ?, ?)";

        PreparedStatement stmtPel = null;
        PreparedStatement stmtAct = null;
        ResultSet rsKeys = null;
        long idPelicula;
        try {
            conexion.setAutoCommit(false);

            idPelicula = p.getId();

            // Si el ID es 0 → MySQL autogenera
            if (idPelicula == 0) {
                // Insertar película (ID autogenerado)
                stmtPel = conexion.prepareStatement(sqlPel, Statement.RETURN_GENERATED_KEYS);
                stmtPel.setString(1, p.getTitulo());
                stmtPel.setInt(2, p.getDuracion());
                stmtPel.executeUpdate();

                rsKeys = stmtPel.getGeneratedKeys();
                if (rsKeys.next()) {
                    p.setId(rsKeys.getLong(1));
                }
            }

            // Insertar actores
            stmtAct = conexion.prepareStatement(sqlAct);
            for (Actor a : p.getListaActores()) {
                stmtAct.setLong(1, p.getId());
                stmtAct.setString(2, a.getNombre());
                stmtAct.setInt(3, a.getEdad());
                stmtAct.setString(4, a.getPersonaje());
                stmtAct.executeUpdate();
            }

            conexion.commit();

        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException ex) {
            }
            throw new ExcepcionRepositorio("Error insertando película", e);

        } finally {
            try {

                if (stmtPel != null) {
                    stmtPel.close();
                }

                if (stmtAct != null) {
                    stmtAct.close();
                }

            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar los recursos" + e);
            }
        }
    }

    public List<Pelicula> listar() {

        String sql = "SELECT * FROM vista_peliculas_actores"; //falta crear la vista

        List<Pelicula> peliculas = new ArrayList<>();
        Pelicula peliculaActual = null;
        long idPeliculaActual = -1;
        int idActor;
        PreparedStatement stmt;
        ResultSet rs;

        try  {
             stmt = conexion.prepareStatement(sql);
             rs = stmt.executeQuery();

            while (rs.next()) {

                long idPelicula = rs.getLong("pelicula_id");

                // Si cambia la película, creamos una nueva
                if (peliculaActual == null || idPelicula != idPeliculaActual) {

                    peliculaActual = new Pelicula(
                            idPelicula,
                            rs.getString("titulo"),
                            rs.getInt("duracion"),
                            new ArrayList<>()
                    );

                    peliculas.add(peliculaActual);
                    idPeliculaActual = idPelicula;
                }

                // Si hay actor, lo añadimos
                idActor = rs.getInt("actor_id");
                if (!rs.wasNull()) {
                    peliculaActual.getListaActores().add(
                            new Actor(
                                    idActor,
                                    rs.getString("nombre"),
                                    rs.getInt("edad"),
                                    rs.getString("personaje")
                            )
                    );
                }
            }

        } catch (SQLException e) {
            throw new ExcepcionRepositorio("Error listando películas (vista)", e);
        }

        return peliculas;
    }

    public void actualizar(Pelicula pelicula) {

        PreparedStatement st = null;
        PreparedStatement stActor = null;

        String sql = "UPDATE pelicula SET titulo = ?, duracion = ? WHERE id = ?";
        String sqlActor = "UPDATE actor SET nombre = ?, edad = ?, personaje = ? WHERE id = ?";

        try {
            conexion.setAutoCommit(false);

            // UPDATE PELICULA
            st = conexion.prepareStatement(sql);
            st.setString(1, pelicula.getTitulo());
            st.setInt(2, pelicula.getDuracion());
            st.setLong(3, pelicula.getId());
            st.executeUpdate();

            // UPDATE ACTORES
            stActor = conexion.prepareStatement(sqlActor);
            for (Actor act : pelicula.getListaActores()) {
                stActor.setString(1, act.getNombre());
                stActor.setInt(2, act.getEdad());
                stActor.setString(3, act.getPersonaje());
                stActor.setLong(4, act.getId());
                stActor.executeUpdate();
            }

            conexion.commit();

        } catch (SQLException e) {
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new ExcepcionRepositorio("Error al actualizar película", e);

        } finally {
            try {
                if (stActor != null) {
                    stActor.close();
                }
                if (st != null) {
                    st.close();
                }
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                throw new ExcepcionRepositorio("Error cerrando recursos", e);
            }
        }
    }

    public void borrar(int id) {

        PreparedStatement st = null;
        String sql = "DELETE FROM pelicula WHERE id = ?";

        try {
            conexion.setAutoCommit(false);

            st = conexion.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();

            conexion.commit();

        } catch (SQLException e) {
            try {
                if (conexion != null){
                    conexion.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Error borrando película", e);

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error cerrando recursos", e);
            }
        }
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error cerrando la conexión", e);
        }
    }


}
