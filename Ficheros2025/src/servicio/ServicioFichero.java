package servicio;

import modelo.Incidencia;
import modelo.ListaIncidencias;
import repositorio.Fichero;
import vista.Consola;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServicioFichero {


    public static String formatearCadena(Incidencia incidencia) {

        DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String fechaStr = incidencia.getFecha().format(fechaFormatter);
        String horaStr = incidencia.getFecha().format(horaFormatter);

        return fechaStr + ";" + horaStr + ";" + incidencia.getExcepcion() + ";" + incidencia.getUsuario();
    }

    public static void guardar(String nombre, LocalDateTime fecha, String excepcion) {

        Incidencia incidencia = new Incidencia(nombre, fecha, excepcion);
        Fichero miFichero = new Fichero("datos/datos.txt");
        miFichero.AddDato(formatearCadena(incidencia));
    }

    public static void leerIncidenciaPorUsuario(String usuario) {

        Fichero miFichero = new Fichero("datos/datos.txt");

        //leer el fichero en forma de listas de tipo String
        List<String> lineas = miFichero.leerFichero();

        //creo una lista para poder meter los usuarios ya filtrados
        ArrayList<Incidencia> filtradas = new ArrayList<>();

        for (String linea : lineas) { //recorremos la lista de tipo String
            Incidencia inc = Incidencia.aObjeto(linea); //cogemos la cadena (String) y la casteamos a Objeto de tipo Incidencia

            if (inc.getUsuario().equalsIgnoreCase(usuario)) {
                filtradas.add(inc);
            }
        }

        //Muestra las incidencias
        if (filtradas.isEmpty()) {
            Consola.mostrarString("No hay incidencias para el usuario: " + usuario);
        } else {
            Consola.mostrarString("Incidencias del usuario " + usuario + ":");
            for (Incidencia i : filtradas) {
                Consola.mostrarString(i.toString());
            }
        }
    }

    public static void leerIncidenciaFecha(String fecha) {

        Fichero miFichero = new Fichero("datos/datos.txt");

        //leer el fichero en forma de listas de tipo String
        List<String> lineas = miFichero.leerFichero();


        //creo una lista para poder meter los usuarios ya filtrados
        ArrayList<Incidencia> filtradas = new ArrayList<>();

        for (String linea : lineas) { //recorremos la lista de tipo String
            Incidencia inc = Incidencia.aObjeto(linea); //cogemos la cadena (String) y la casteamos a Objeto de tipo Incidencia

            if (inc.getFecha().equals(fecha)) {
                filtradas.add(inc);
            }
        }

        //Muestra las incidencias
        if (filtradas.isEmpty()) {
            Consola.mostrarString("No hay incidencias para el usuario: " + fecha);
        } else {
            Consola.mostrarString("Incidencias del usuario " + fecha + ":");
            for (Incidencia i : filtradas) {
                Consola.mostrarString(i.toString());
            }
        }
    }


}
