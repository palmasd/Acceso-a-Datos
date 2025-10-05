package modelo;

import java.util.ArrayList;

public class ListaIncidencias {

    private ArrayList<Incidencia> lista;

    public ListaIncidencias() {
        this.lista = new ArrayList<>();
    }

    public ArrayList<Incidencia> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Incidencia> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "ListaIncidencias{" +
                "lista=" + lista +
                '}';
    }
}
