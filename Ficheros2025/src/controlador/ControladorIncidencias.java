package controlador;

import Excepciones.ExcepcionPersonalizada;
import Excepciones.ValidarFechas;
import servicio.ServicioFichero;
import vista.Consola;
import vista.Escaner;
import static Excepciones.ValidarUsuario.UsuarioValido;

import java.time.LocalDateTime;
import java.util.InputMismatchException;

public class ControladorIncidencias {

    public static void iniciar() throws ExcepcionPersonalizada {

        boolean usuarioValido = false;
        boolean opcionValida = false;
        String usuario = "";
        String buscarUsuario = "";
        String buscarFechaInicio;
        String buscarFechaFin;
        int opcion;
        String input;
        boolean salida = false;

        while (!usuarioValido) {
            try {
                usuario = UsuarioValido("Escribe tu primer usuario");
                usuarioValido = true;

            } catch (ExcepcionPersonalizada e) {
                Consola.mostrarString("Error: " + e.getMessage());
            }
        }
         do {
             boolean fechaValidaBusqueda = false;

             try {
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
                            while (!usuarioValidarBusqueda) {
                                try {
                                    buscarUsuario = UsuarioValido("Buscar Incidencia por Usuario");
                                    ServicioFichero.leerIncidenciaPorUsuario(buscarUsuario);
                                    usuarioValidarBusqueda = true;
                                } catch (ExcepcionPersonalizada e) {
                                    Consola.mostrarString(e.getMessage());
                                }
                            }
                            break;
                        case 3:
                            while (!fechaValidaBusqueda) {
                                try {
                                    buscarFechaInicio = ValidarFechas.FechaValida("Introduce la primera fecha");
                                    buscarFechaFin = ValidarFechas.FechaValida("Introduce la segunda fecha");
                                    ServicioFichero.leerIncidenciaFecha(buscarFechaInicio, buscarFechaFin);
                                    fechaValidaBusqueda = true;
                                } catch (ExcepcionPersonalizada e) {
                                    Consola.mostrarString(e.getMessage());
                                }
                            }
                            break;
                        case 4:
                            salida = true;
                            Consola.mostrarString("Has salido del programa");
                            break;

                        default:
                            Consola.mostrarString("Opcion no valida");
                            break;
                    }

                } catch (InputMismatchException e) {
                    Consola.mostrarString("Solamente se admiten datos numericos");
                    Escaner.limpiarBuffer(); //limpiar el buffer del Scanner
                }
            } while (!salida);
         Escaner.cerrarBuffer();//cerrar el Scanner al salir del menu
    }
}
