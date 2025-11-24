package org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {

    private long id;
    private String titulo;
    private int duracion;
    private List<Actor> listaActores;

    public Pelicula(long id, String titulo, int duracion, List<Pelicula> listaActores) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.listaActores = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setGenero(int duracion) {
        duracion = duracion;
    }

    public List<Actor> getListaActores() {
        return listaActores;
    }

    public void setListaActores(List<Actor> listaActores) {
        this.listaActores = listaActores;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", duracion='" + duracion + '\'' +
                ", listaActores=" + listaActores +
                '}';
    }
}

