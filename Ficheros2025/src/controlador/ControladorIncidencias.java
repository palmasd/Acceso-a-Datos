package controlador;

import Excepciones.ExcepcionPersonalizada;
import Excepciones.ValidarFechas;
import servicio.ServicioFichero;
import vista.Consola;
import vista.Escaner;
import static Excepciones.ValidarUsuario.UsuarioValido;

import java.time.LocalDateTime;

public class ControladorIncidencias {

    public static void iniciar() throws ExcepcionPersonalizada {

        boolean usuarioValido = false;
        String usuario = "";
        String buscarUsuario = "";
        String buscarFechaInicio;
        String buscarFechaFin;
        int opcion;
        String input;

        while (!usuarioValido) {
            try {
                usuario = UsuarioValido("Escribe tu primer usuario");
                usuarioValido = true;

            } catch (ExcepcionPersonalizada e) {
                Consola.mostrarString("Error: " + e.getMessage());
            }
        }

        do {
            Consola.mostrarMenu();
            opcion = Escaner.leerEntero();

            switch (opcion) {
                case 1:
                    Consola.mostrarString("Crear Incidencia");
                    try {
                        input = Escaner.pedirString("Introduce una letra para provocar la excepcion");

                        try {
                            Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            throw new ExcepcionPersonalizada("Error: se esperaba un número pero metiste: " + input);
                        }
                    } catch (ExcepcionPersonalizada e) {
                        Consola.mostrarString("Excepcion capturada: " + e.getMessage());
                        ServicioFichero.guardar(e.toString(), LocalDateTime.now(), usuario);
                    }
                    break;
                case 2:
                    boolean usuarioValidarBusqueda = false;
                    while(!usuarioValidarBusqueda){
                        try {
                            buscarUsuario = UsuarioValido("Buscar Incidencia por Usuario");
                            ServicioFichero.leerIncidenciaPorUsuario(buscarUsuario);
                            usuarioValidarBusqueda = true;
                        }catch (ExcepcionPersonalizada e){
                            Consola.mostrarString(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    boolean fechaValidaBusqueda = false;
                    while(!fechaValidaBusqueda){
                        try{
                            buscarFechaInicio = ValidarFechas.FechaValida("Introduce la primera fecha");
                            buscarFechaFin = ValidarFechas.FechaValida("Introduce la segunda fecha");
                            ServicioFichero.leerIncidenciaFecha(buscarFechaInicio, buscarFechaFin);
                            fechaValidaBusqueda = true;
                        }catch (ExcepcionPersonalizada e){
                            Consola.mostrarString(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    Consola.mostrarString("Has salido del programa");
                    break;
                default:
                    Consola.mostrarString("Opcion no valida");
            }
        } while (opcion != 4);
    }
}
