package org.example.modelo;

import java.util.List;

public class ListaPeliculas {
    private List<Pelicula> listaPeliculas;

    public ListaPeliculas(List<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }



    public List<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public void setListaPeliculas(List<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    @Override
    public String toString() {
        return "ListaPeliculas{" +
                "listaPeliculas=" + listaPeliculas +
                '}';
    }


}
