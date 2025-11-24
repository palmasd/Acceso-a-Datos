package org.example.servicio;

import org.example.modelo.Pelicula;
import org.example.repositorio.RepositorioPelicula;

import java.util.List;

public class ServicioPelicula {

    private RepositorioPelicula repo;

    public ServicioPelicula(String fichero) {
        repo = new RepositorioPelicula(fichero);
    }

    public void agregar(Pelicula pelicula){
        repo.guardar(pelicula);
    }

    public List<Pelicula> obtenerTodas(){
        return repo.listar();
    }

    public void actualizar(Pelicula pelicula){
        repo.actualizar(pelicula);
    }

    public void eliminar(int id){
        repo.borrar(id);
    }

}
