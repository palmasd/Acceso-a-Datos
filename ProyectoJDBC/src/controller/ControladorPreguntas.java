package controller;

import model.Preguntas;
import servicio.ServicioDB;
import servicio.ServicioFichero;
import model.DBConnection;

import java.sql.SQLException;
import java.util.List;

public class ControladorPreguntas {

    public static void iniciar() {
        ServicioDB servicioDB = null;
        int cantidadPreguntas;
        ServicioFichero servicioFichero;

        try {
            servicioDB = new ServicioDB();
            System.out.println("conectado");

            //leer el fichero
            servicioFichero = new ServicioFichero("datos/prguntas.txt");

            List<Preguntas> pregunta = servicioFichero.parseoListaPregunta();

            servicioDB.cargarPreguntas(pregunta);

        } catch (SQLException e) {
            e.getErrorCode();
        }finally {
            try{
                if(DBConnection.getConnection().isClosed()){
                    servicioDB.cerrarConexion();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
