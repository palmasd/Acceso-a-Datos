package org.example.consola;

import org.example.controlador.ControladorTotal;
import org.example.modelo.Actor;
import org.example.modelo.Pelicula;
import org.example.repositorio.*;
import org.example.servicio.IRepositorio;
import org.example.servicio.Servicio;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private ControladorTotal controladorTotal;


    public Menu() {

    }

    public void iniciar() {

        // PRIMERA PARTE: seleccionar repositorio
        Consola.mostrarString("=== Selecciona el tipo de almacenamiento ===");
        Consola.mostrarString("1. XML");
        Consola.mostrarString("2. JSON");
        Consola.mostrarString("3. MySQL");
        Consola.mostrarString("4. MongoDB");
        Consola.mostrarString("5. PostgreSQL");

        int opcion = Escaner.leerEntero("Selecciona una opcion: ");
        Escaner.limpiarBuffer();// Consumir el salto de línea
        IRepositorio repositorio = null;

        switch (opcion) {
            case 1:
                repositorio = new RepositorioXML("data/peliculas.xml");
                break;
            case 2:
                repositorio = new RepositorioJSON("data/peliculas.json");
                break;
            case 3:
                repositorio = new RepositorioPelicula();

                break;
            case 4:
                repositorio = new RepositorioMongo();
                break;
            case 5:
                repositorio = new RepositorioPostgreSql();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }

        Servicio servicio = new Servicio(repositorio);
        this.controladorTotal = new ControladorTotal(servicio);
        boolean flag = true;

        while (flag) {

            Consola.mostrarString("\n=== MENÚ PELÍCULAS ===");
            Consola.mostrarString("1. Crear película");
            Consola.mostrarString("2. Listar películas");
            Consola.mostrarString("3. Actualizar película");
            Consola.mostrarString("4. Borrar película");
            Consola.mostrarString("5. Salir");

            int opciones = Escaner.leerEntero("Elige una opción: ");

            int id;
            String titulo;
            int duracion;
            List<Actor> listaActores = new ArrayList<>();
            int idActor;
            String nombre;
            int edad;
            String personaje;

            int idActualizado;
            String nuevoTitulo;
            int nuevaDuracion;
            List<Actor> nuevaListaActores;
            int nuevoIdActor;
            String nuevoNombre;
            int nuevaEdad;
            String nuevoPersonaje;

            int idBorrar;
            int numActores;

            switch (opciones) {

                case 1:

                    id = Escaner.leerEntero("Dime ID de la Pelicula (0 para autogenerar):");
                    titulo = Escaner.pedirString("Dime Titulo de la Pelicula");
                    duracion = Escaner.leerEntero("Dime la duracion (minutos) de la Pelicula");

                    numActores = Escaner.leerEntero("Dime cuantos Actores quieres");
                    for (int i = 0; i < numActores; i++) {
                        Consola.mostrarString("Actor numero #" + (i + 1));
                        idActor = Escaner.leerEntero("Dime el ID del Actor");
                        nombre = Escaner.pedirString("Dime el nombre del Actor");
                        edad = Escaner.leerEntero("Dime la edad del Actor");
                        personaje = Escaner.pedirString("Dime el personaje del Actor");

                        listaActores.add(new Actor(idActor, nombre, edad, personaje));

                    }

                    controladorTotal.guardar(id, titulo, duracion, listaActores);
                    Consola.mostrarString("Pelicula guardada correctamente");
                    break;

                case 2:

                    List<Pelicula> lista = controladorTotal.listar();
                    Consola.mostrarString("LISTA DE PELICULAS");
                    for (Pelicula pelis: lista){
                        Consola.mostrarString(" " + pelis);
                    }

                    break;

                case 3:
                    idActualizado = Escaner.leerEntero("Dime ID de la Pelicula: ");

                    Pelicula peliculaActual = controladorTotal.buscarPorId(idActualizado); //buscar la peli por su id la guardamos en un objeto de pelicula

                    if (peliculaActual != null) {

                        //ACTUALIZAR TITULO
                        String respTitulo = Escaner.pedirString("Actualizar título? (s/n)");
                            if (respTitulo.equalsIgnoreCase("s")) {
                                nuevoTitulo = Escaner.pedirString("Nuevo Titulo: ");
                            } else {
                                nuevoTitulo = peliculaActual.getTitulo(); //si no quieres actualizar el titulo te quedas con el actual
                            }

                         //ACTUALIZAR DURACION
                        String respDuracion = Escaner.pedirString("Actualizar Duracion? (s/n)");
                            if (respDuracion.equalsIgnoreCase("s")){
                                nuevaDuracion = Escaner.leerEntero("Nueva Duracion");
                            } else {
                                nuevaDuracion = peliculaActual.getDuracion();
                            }

                        //ACTUALIZAR ACTORES
                        String respActores = Escaner.pedirString("Actualizar Actores? (s/n)");
                        if (respActores.equalsIgnoreCase("s")) {
                            nuevaListaActores = new ArrayList<>();
                            numActores = Escaner.leerEntero("Dime cuantos Actores quieres");
                            for (int i = 0; i < numActores; i++) {
                                Consola.mostrarString("Actor numero #" + (i + 1));
                                nuevoIdActor = Escaner.leerEntero("Dime el ID del Actor");
                                nuevoNombre = Escaner.pedirString("Dime el nombre del Actor");
                                nuevaEdad = Escaner.leerEntero("Dime la edad del Actor");
                                nuevoPersonaje = Escaner.pedirString("Dime el personaje del Actor");

                                nuevaListaActores.add(new Actor(nuevoIdActor, nuevoNombre, nuevaEdad, nuevoPersonaje));
                            }
                        } else{
                            nuevaListaActores = peliculaActual.getListaActores(); //si el usuario no quiere actualizar actores, se queda la lista de actores actuales
                        }

                        controladorTotal.actualizar(idActualizado, nuevoTitulo, nuevaDuracion, nuevaListaActores);
                        Consola.mostrarString("Pelicula actualizada correctamente");
                    }else {
                        Consola.mostrarString("Pelicula con ese id no encontrada");
                    }
                    break;

                case 4:
                    idBorrar = Escaner.leerEntero("ID de la pelicula a borrar");
                    controladorTotal.borrarPelicula(idBorrar);
                    Consola.mostrarString("Pelicula eliminada correctamente");
                    break;

                case 5:
                    flag = false;
                    Consola.mostrarString("aplicacion Cerrada correctamene");
                    // Cerrar conexión si el repositorio tiene método de cierre
                    if (repositorio instanceof RepositorioPelicula) {
                        ((RepositorioPelicula) repositorio).cerrarConexion();
                    }
                    break;
                default:
                    Consola.mostrarString("Opción Inválida");
                    break;
            }

        }

    }

}
