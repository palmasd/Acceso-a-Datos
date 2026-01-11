package org.example.servicio;

import org.example.modelo.Pelicula;

import java.util.List;

public class Servicio {

    private IRepositorio repositorio;

    public Servicio(IRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void guardar(Pelicula p ){
        repositorio.guardar(p);
    }

    public List<Pelicula> listar(){
        return repositorio.listar();
    }

    public Pelicula buscarPorId(int id) {
        List<Pelicula> lista = listar();
        for (Pelicula p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void actualizar(Pelicula  p){
        repositorio.actualizar(p);
    }

    public void borrar(int id){
        repositorio.borrar(id);
    }

}
