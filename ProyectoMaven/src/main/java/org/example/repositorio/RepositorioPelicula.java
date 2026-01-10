package org.example.repositorio;

import org.example.modelo.Actor;
import org.example.modelo.Pelicula;
import org.example.servicio.IRepositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPelicula implements IRepositorio {

    private Connection conexion;

    public RepositorioPelicula() {
        this.conexion = conexion;
    }

    public void guardar(Pelicula pelicula){

        List<Actor> Actores = pelicula.getListaActores();

        String sql = "INSERT INTO PELICULA(id, titulo, duracion) VALUE (?, ? ,?)";
        String sqlActor = "INSERT INTO Actores(nombre, edad, personaje) VALUE (?, ? ,?)";
        PreparedStatement stPeliculas = null;
        PreparedStatement stActores = null;


        try{
            conexion.setAutoCommit(false);

            //PrepareStatement para tablas Peliculas
            stPeliculas = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stPeliculas.setLong(1, pelicula.getId());
            stPeliculas.setString(1, pelicula.getTitulo());
            stPeliculas.setInt(1, pelicula.getDuracion());

            stPeliculas.executeUpdate(); //este es para los INSERT / DELETE / UPDATE


            //PrepareStatement para tablas Actores
            stActores = conexion.prepareStatement(sqlActor, Statement.RETURN_GENERATED_KEYS);

            for (Actor act: Actores){
                stActores.setString(1, act.getNombre());
                stActores.setInt(2, act.getEdad());
                stActores.setString(3, act.getPersonaje());

                stActores.executeUpdate();
            }

            conexion.commit();

        } catch (SQLException e) {
            try {
                if (conexion != null) {
                    conexion.rollback(); // ¡Error! Deshacer todo
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            throw new RuntimeException("Error en la conexion a base de datos " + e);
        } finally {
                try {

                    if (stPeliculas != null){
                        stPeliculas.close();
                    }

                    if (stActores != null){
                        stActores.close();
                    }

                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Error al cerrar los recursos"+ e);
                }
        }
    }

    public List<Pelicula> listar(){

        String sql = "SELECT * FROM PELICULA";
        PreparedStatement stPeliculas = null;
        ResultSet rs = null;
        Pelicula pelis = null;
        List<Pelicula> listaPelis = new ArrayList<>();

        try{

            stPeliculas = conexion.prepareStatement(sql);

            rs = stPeliculas.executeQuery(); // el executeQuery para las lecturas

            while(rs.next()){

                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                int duracion = rs.getInt("duracion");
                pelis = new Pelicula(id, titulo, duracion, new ArrayList<>());

                for (Pelicula p: listaPelis){
                    int idActor = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int edad = rs.getInt("edad");
                    String personaje = rs.getString("personaje");
                    p.getListaActores().add(new Actor(idActor, nombre, edad, personaje));
                }
                listaPelis.add(pelis);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listaPelis;
    }

    public void actualizar(Pelicula pelicula){

        List<Actor> Actores = pelicula.getListaActores();

        PreparedStatement st = null;
        PreparedStatement stActor = null;
        String sql = "UPDATE Peliculas SET titulo = ?, duracion = ? WHERE id = ?";
        String sqlActor = "UPDATE Actores SET nombre = ?, edad = ?, personaje = ? WHERE id = ?";

        try{
            //Stattement para Peliculas
            st = conexion.prepareStatement(sql);

            st.setString(1, pelicula.getTitulo());
            st.setInt(2, pelicula.getDuracion());
            st.setLong(3, pelicula.getId());
            st.executeUpdate();


            //Stattement para Actores
            stActor = conexion.prepareStatement(sqlActor);

            for (Actor act: Actores){
                stActor.setString(1, act.getNombre());
                stActor.setInt(2, act.getEdad());
                stActor.setString(3, act.getPersonaje());
                stActor.setInt(4, act.getId());

                stActor.executeUpdate();
            }

            conexion.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{

                if (st != null){
                    st.close();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void borrar(int id){
        String tablaPreguntas = "delete from preguntas WHERE id = ? ";

        PreparedStatement statementP = null;

        try{
            statementP = conexion.prepareStatement(tablaPreguntas, Statement.RETURN_GENERATED_KEYS); //

            statementP.setInt(1, id);

            statementP.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
