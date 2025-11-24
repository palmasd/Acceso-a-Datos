package org.example.controlador;

import org.example.servicio.ServicioXML;

public class ControladorPeliculaXML {

    private final ServicioXML servicioXML;

    public ControladorPeliculaXML(String fichero){
        this.servicioXML = new ServicioXML(fichero);
    }

}
