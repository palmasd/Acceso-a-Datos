package org.example.servicio;

import org.example.repositorio.RepositorioJSON;

public class ServicioJSON {

    private final RepositorioJSON repo;


    public ServicioJSON(String fichero){
        this.repo = new RepositorioJSON(fichero);
    }

}
