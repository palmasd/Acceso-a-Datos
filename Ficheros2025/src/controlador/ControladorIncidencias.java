package controlador;

import Excepciones.ExcepcionPersonalizada;
import modelo.Pokemon;
import servicio.ServicioFichero;
import vista.Consola;
import vista.Escaner;

import java.time.LocalDateTime;

public class ControladorIncidencias {


    public static String pedirUsuarioValido() throws ExcepcionPersonalizada {
        String usuario;
        usuario = Escaner.pedirString("Escribe un Usuario");

       if (usuario == null){
           throw new ExcepcionPersonalizada("El usuario no puede ser nulo");
       }
       if (usuario.isEmpty()){
           throw new ExcepcionPersonalizada("El usuario no puede estar vacio");
       }
       if (!usuario.matches("^[a-zA-Z]+$")){
           throw new ExcepcionPersonalizada("El usuario debe tener letras de la -aA- a la -zZ-");
       }

       return usuario;
    }


    public static void iniciar() throws ExcepcionPersonalizada {

//      String tipo = Escaner.pedirString("Nombre Usuario");
//      String nombre = Escaner.pedirString("Nombre pokemon?");
//
//      ServicioFichero.guardar(tipo, nombre); //llamo al servicio que interactua con el modelo
//      ServicioFichero.guardar(new Pokemon(tipo, nombre));


        String buscarUsuario;
        String buscarFecha;
        int opcion;
        boolean usuarioValido = false;
        String usuario = "";
        String input;

        while(!usuarioValido) {
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
                        try{
                            input = Escaner.pedirString("Introduce una letra para provocar la excepcion");
                            //ServicioFichero.guardar(usuario, LocalDateTime.now(), input);

                            try{
                                Integer.parseInt(input);
                            } catch (NumberFormatException e) {
                                throw new ExcepcionPersonalizada("Error: se esperaba un número pero metiste: "+ input);
                            }
                        } catch (ExcepcionPersonalizada e){
                            Consola.mostrarString("Excepcion capturada: " + e.getMessage());
                            ServicioFichero.guardar(usuario, LocalDateTime.now(), e.toString());
                        }

                        break;
                    case 2:
                        buscarUsuario = Escaner.pedirString("Buscar por Usuario");


                        break;
                    case 3:
                        buscarFecha = Escaner.pedirString("Busca la fecha por rango");
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
