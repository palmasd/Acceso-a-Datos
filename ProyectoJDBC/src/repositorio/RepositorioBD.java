package repositorio;

import model.Opciones;
import model.Preguntas;
import view.Consola;

import java.sql.*;
import java.util.List;

public class RepositorioBD {

    private Connection connection;

    public RepositorioBD(Connection connection){
        this.connection=connection;
    }

    public void borraBD(){

    }

    public int insertarPregunta(Preguntas preguntas){
        String sql = "Insert Into preguntas (enunciado) VALUES (?)";  //preparamos la consulta INSERT
        int idGenerado = -1; //es el id que nos va a devolver la base de datos y se inicia a -1 para comprobar si ha fallado
        int filas;

        PreparedStatement statement = null;
        ResultSet rs = null;

        try{
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //preparamos un stmt de la consulta sql y que nos permita recuperar las claves generadas por el AUTOINCREMENT
            statement.setString(1, preguntas.getEnunciado());             //asignar los valores al ?

            //ejecutamos la insercion a la base de datos
             filas = statement.executeUpdate(); //devuelve cuantas filas fueron afectadas y si todo esta bien devuelve 1

            if (filas > 0) { //comprobarmos si se insertaron

                rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getInt(1); //asignar el id generado del ResultSet a nuestra variable
                    Consola.mostrarString("Pregunta agregadad con ID: " + idGenerado);
                }

            }else {
                Consola.mostrarString("No se pudo agregar la Pregunta");
            }

        } catch (SQLException e) {
            Consola.mostrarString("Error al agregar Pregunta" + e.getMessage());
        }finally {
            try{
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e) {
                Consola.mostrarString("Error al cerrar el statement"+ e.getMessage());
            }
        }

        return idGenerado;
    }



    public void insertarOpciones(Opciones opciones, int idPreguntas){
        String sql = "INSERT INTO opciones(ID_Pregunta, etiqueta, texto, esCorrecta) VALUES (?, ? ,?, ?)";
        int fila;
        int idGenerado = -1;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, idPreguntas);
            statement.setString(2, opciones.getEtiqueta());
            statement.setString(3, opciones.getTexto());
            statement.setBoolean(4, opciones.isEsCorrecta());

            fila = statement.executeUpdate();

            if (fila > 0){
                rs = statement.getGeneratedKeys();
                if (rs.next()){
                    idGenerado = rs.getInt(1);
                    Consola.mostrarString("Tabla Opciones generadas con el id: " + idGenerado);
                }
            } else {
                Consola.mostrarString("NO se pudieron agregar las filas de Opciones");
            }

        }catch (SQLException e){
            Consola.mostrarString("Error al agregar Opciones: " + e.getMessage());
        }finally {
            try{
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e) {
                Consola.mostrarString("Error al cerras los recursos");
            }
        }

    }

    public List<Preguntas> cargarPreguntas(){


        return null;
    }

}
