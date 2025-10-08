package servicio;

import modelo.Incidencia;
import repositorio.Fichero;
import utils.FormatoFecha;
import vista.Consola;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioFichero {

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

        for (String linea : lineas) {                           //recorremos la lista de tipo String
            Incidencia inc = aObjeto(linea);                    //cogemos la cadena (String) y la casteamos a Objeto de tipo Incidencia

            if (inc.getUsuario().equalsIgnoreCase(usuario)) {
                filtradas.add(inc);
            }
        }

        //Muestra las incidencias
        if (filtradas.isEmpty()) {
            Consola.mostrarString("No hay incidencias para el usuario: " + usuario + "\n");
        } else {
            Consola.mostrarString("Incidencias del usuario " + usuario + ":\n");
            for (Incidencia i : filtradas) {
                Consola.mostrarString(i.toString());
            }
        }
    }

    public static void leerIncidenciaFecha(String fechaInicio, String fechaFin) {

        Fichero miFichero = new Fichero("datos/datos.txt");

        //leer el fichero en forma de listas de tipo String
        List<String> lineas = miFichero.leerFichero();

        //creo una lista para poder meter los usuarios ya filtrados
        ArrayList<Incidencia> filtradas = new ArrayList<>();

        //Formateamos las fechas que vienen en String para pasarlas a LocalDateTme y asi leerlas correctamente
        LocalDateTime fechaIni = FormatoFecha.parsearFechaHora(fechaInicio);
        LocalDateTime fechaFinal = FormatoFecha.parsearFechaHora(fechaFin);

        for (String linea : lineas) { //recorremos la lista de tipo String
            Incidencia inc = aObjeto(linea); //cogemos la cadena (String) y la casteamos a Objeto de tipo Incidencia

            if (!inc.getFecha().isBefore(fechaIni) && !inc.getFecha().isAfter(fechaFinal)) {
                filtradas.add(inc);
            }
        }

        //Muestra las incidencias
        if (filtradas.isEmpty()) {
            Consola.mostrarString("No hay incidencias para esas fechas: " + fechaInicio + fechaFin);
        } else {
            Consola.mostrarString("Incidencias desde: " + fechaInicio + " hasta " + fechaFin);
            for (Incidencia i : filtradas) {
                Consola.mostrarString(i.toString());
            }
        }
    }

    //metodo para castear los String que se leen en el fichero pasarlos a objetos
    public static Incidencia aObjeto(String cadena) {
        String[] dividido = cadena.split(";"); //lee el fichero en String y los mete en un array sin " ; "

        String fechaHoraStr = dividido[0] + " " + dividido[1]; // en las dos primeras posiciones fecha y hora los tranforma en uno solo;
        LocalDateTime fechaHora = FormatoFecha.parsearFechaHora(fechaHoraStr); //recibo el formato que debo utilizar y lo paso a LocalDateTime

        //Usuario-fecha/hora-Excepcion
        return new Incidencia(dividido[2], fechaHora, dividido[3]);
    }


    public static String formatearCadena(Incidencia incidencia) {

        //Formateamos la fecha de LocalDateTime para dividirlo entre la fecha y la hora y luego poder insertar correctamente
        String fechaStr = FormatoFecha.formatearFecha(incidencia.getFecha());
        String horaStr = FormatoFecha.formatearHora(incidencia.getFecha());

        return fechaStr + ";" + horaStr + ";" + incidencia.getExcepcion() + ";" + incidencia.getUsuario();
    }
}
