package org.example.controlador;

import org.example.consola.Menu;
import org.example.modelo.Actor;
import org.example.modelo.Pelicula;
import org.example.servicio.*;

import java.sql.PreparedStatement;
import java.util.List;

public class ControladorTotal {

    Servicio servicio;

    public ControladorTotal(Servicio servicio) {

        this.servicio = servicio;

    }

    public void guardar(int id, String titulo, int duracion, List<Actor> listaActores){
        Pelicula pelicula = new Pelicula(id, titulo, duracion, listaActores);
        servicio.guardar(pelicula);
    }

    public List<Pelicula> listar(){
        return servicio.listar();
    }

    public Pelicula buscarPorId(int id){
        return servicio.buscarPorId(id);
    }

    public void actualizar(int idNuevo, String nuevoTitulo, int nuevaDuracion, List<Actor> nuevosActores){
        Pelicula pelicula = new Pelicula(idNuevo, nuevoTitulo, nuevaDuracion, nuevosActores);
        servicio.actualizar(pelicula);
    }

    public void borrarPelicula(int id){
        servicio.borrar(id);
    }


}
