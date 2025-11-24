package org.example.servicio;

import org.example.repositorio.RepositorioXML;

public class ServicioXML {

    private final RepositorioXML repo;

    public ServicioXML(String fichero){
        this.repo = new RepositorioXML(fichero);
    }

}
