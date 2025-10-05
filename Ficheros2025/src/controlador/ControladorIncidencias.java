package controlador;

import Excepciones.ExcepcionPersonalizada;
import servicio.ServicioFichero;
import vista.Consola;
import vista.Escaner;
import static Excepciones.ValidarUsuario.pedirUsuarioValido;

import java.time.LocalDateTime;


public class ControladorIncidencias {

    public static void iniciar()  {

        boolean usuarioValido = false;
        String usuario = "";
        String buscarUsuario;
        String buscarFechaInicio;
        String buscarFechaFin;
        int opcion;
        String input;


        while (!usuarioValido) {
            try {
                usuario = pedirUsuarioValido();
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
                    buscarUsuario = Escaner.pedirString("Buscar por Usuario");
                    ServicioFichero.leerIncidenciaPorUsuario(buscarUsuario);
                    break;
                case 3:
                    buscarFechaInicio = Escaner.pedirString("Introduce la primera fecha");
                    buscarFechaFin = Escaner.pedirString("Introduce la segunda fecha");
                    ServicioFichero.leerIncidenciaFecha(buscarFechaInicio, buscarFechaFin);
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
