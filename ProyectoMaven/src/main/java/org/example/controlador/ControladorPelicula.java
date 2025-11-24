package org.example.controlador;

import org.example.servicio.ServicioPelicula;

public class ControladorPelicula {

    private ServicioPelicula servicioPelicula;

    public ControladorPelicula(String fichero){
        this.servicioPelicula = new ServicioPelicula(fichero);
    }

}
