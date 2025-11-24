package org.example.controlador;

import org.example.servicio.ServicioJSON;

public class ControladorJSON {

    private ServicioJSON servicioJSON;


    public ControladorJSON(String fichero) {
        this.servicioJSON = new ServicioJSON(fichero);
    }
}
