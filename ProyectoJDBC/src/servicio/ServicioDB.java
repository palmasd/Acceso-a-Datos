package servicio;

import model.Opciones;
import model.Preguntas;
import repositorio.RepositorioBD;
import model.DBConnection;
import view.Consola;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class ServicioDB {

    private Connection connection = null;
    private RepositorioBD repo = null;

    public ServicioDB() throws SQLException {
        try {
            //abrir la conexion solamente una vez
            connection = DBConnection.getConnection();
            //crear el repositorio pasandole la conexion
            repo = new RepositorioBD(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void cargarPreguntas(List<Preguntas> listaPregunta) {
        int idGenerado;
        for (Preguntas p : listaPregunta) {
            idGenerado = repo.insertarPregunta(p);
            for (Opciones opciones: p.getOpciones()){
                repo.insertarOpciones(opciones, idGenerado);
            }
        }
    }

    //metodo para cerrar la conexion si no es nula o si no esta cerrada
    public void cerrarConexion() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                Consola.mostrarString("Conexion cerrada Correctamente");
            }
        } catch (SQLException e) {
            Consola.mostrarString("Error al cerrar la conexion: " + e.getMessage());
        }
    }

}
