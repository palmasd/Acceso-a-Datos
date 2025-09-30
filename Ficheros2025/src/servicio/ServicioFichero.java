package servicio;

import modelo.Incidencia;
import modelo.ListaIncidencias;
import repositorio.Fichero;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ServicioFichero {


    public static String formatearCadena(Incidencia incidencia) {

        DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter horaFormatter  = DateTimeFormatter.ofPattern("HH:mm:ss");

        String fechaStr = incidencia.getFecha().format(fechaFormatter);
        String horaStr  = incidencia.getFecha().format(horaFormatter);

        return fechaStr + ";" + horaStr + ";" + incidencia.getExcepcion() + ";" + incidencia.getUsuario();
    }

    public static void guardar(String nombre, LocalDateTime fecha, String excepcion){

    Incidencia incidencia = new Incidencia(nombre, fecha, excepcion);
    Fichero miFichero = new Fichero("datos/datos.txt");
    miFichero.AddDato(formatearCadena(incidencia));
    }

    public static void leerFicheroPorUsuario(String usuario) {

        ArrayList<ListaIncidencias> misIncidencias = new ArrayList<>();
        Fichero miFichero = new Fichero("datos/datos.txt");
        miFichero.leerFichero(misIncidencias);
    }


}
