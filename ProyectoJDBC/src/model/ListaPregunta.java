package model;

import java.util.ArrayList;
import java.util.List;

public class ListaPregunta {

    private List<Preguntas> listaPreguntas;

    public ListaPregunta() {
        this.listaPreguntas = new ArrayList<>();
    }

    public List<Preguntas> getListaPreguntas() {
        return listaPreguntas;
    }

    public void setListaPreguntas(List<Preguntas> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    @Override
    public String toString() {
        return "ListaPregunta{" +
                "listaPreguntas=" + listaPreguntas +
                '}';
    }



}
