package org.example.servicio;

import org.example.modelo.Pelicula;

import java.util.List;

public interface IRepositorio {

    void guardar(Pelicula p);
    List<Pelicula> listar();
    void actualizar(Pelicula p);
    void borrar(int id);

}
