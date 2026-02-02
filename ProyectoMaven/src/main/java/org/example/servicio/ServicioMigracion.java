package org.example.servicio;

import org.example.modelo.Pelicula;
import java.util.List;

public class ServicioMigracion {

    private IRepositorio origen;
    private IRepositorio destino;

    public ServicioMigracion(IRepositorio origen, IRepositorio destino) {
        this.origen = origen;
        this.destino = destino;
    }
//aqui guardamos la lista del repositorio en una lista de ese mismo objeto
    public void migrarTodo() {
        List<Pelicula> peliculas = origen.listar();

        for (Pelicula p : peliculas) { //y aqui lo que hacemos es recorrer esa lista para insertarlas en el nuevo repositorio
            destino.guardar(p);
        }
    }
}
