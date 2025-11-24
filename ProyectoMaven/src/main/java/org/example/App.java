package org.example;

import org.example.controlador.ControladorJSON;
import org.example.controlador.ControladorPelicula;
import org.example.controlador.ControladorPeliculaXML;
import org.example.modelo.Pelicula;
import org.example.repositorio.RepositorioJSON;
import org.example.repositorio.RepositorioXML;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {

    private final ControladorPelicula controladorPelicula;
    private final ControladorPeliculaXML controladorPeliculaXML;
    private final ControladorJSON controladorJSON;

    public App(){
        this.controladorJSON = new ControladorJSON("data/pelicula.json");
        this.controladorPeliculaXML = new ControladorPeliculaXML("data/pelicula.xml");
        this.controladorPelicula = new ControladorPelicula("data/pelicula");
    }

    public static void main( String[] args ) {

        RepositorioXML fichero1 = new RepositorioXML("data/peliculas.xml");
        fichero1.guardar(new Pelicula(1, "starwars", 120, new ArrayList<>()));

        RepositorioJSON fichero = new RepositorioJSON("data/peliculas.json");
        fichero.guardar(new Pelicula(1, "starwars", 120, new ArrayList<>()));

    }
}
