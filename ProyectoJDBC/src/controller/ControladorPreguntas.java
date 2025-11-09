package controller;

import model.Usuario;
import servicio.ServicioFichero;
import util.DBConnection;
import view.Consola;
import view.Escaner;

import java.sql.SQLException;

public class ControladorPreguntas {

    public static void iniciar(){

        int cantidadPreguntas;


       // //pedir datos al usuario para que pida una pregunta
       // Consola.mostrarString("Dime cuantas preguntas quieres?");
       // cantidadPreguntas = Escaner.leerEntero();


        //leer el fichero
        ServicioFichero servicioFichero;
        servicioFichero = new ServicioFichero("datos/prguntas.txt");
        System.out.println(servicioFichero.parseoListaPregunta());


        try {
            DBConnection.getConnection();
            System.out.println("conectado");
            //Usuario usuario = new Usuario("carlos", 21);
            //usuario.generarInsertar(usuario);
        } catch (SQLException e) {
            e.getErrorCode();
        }
    }
}
