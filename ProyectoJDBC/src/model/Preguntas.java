package model;

import servicio.ServicioFichero;

import java.util.List;

public class Preguntas {

    private final int ID;
    private String enunciado;
    private List<Opciones> opciones;

    public Preguntas(int id, String enunciado, List<Opciones> opciones) {
        ID = id;
        this.enunciado = enunciado;
        this.opciones = opciones;
    }

    public List<Opciones> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opciones> opciones) {
        this.opciones = opciones;
    }

    public int getID() {
        return ID;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.ID).append(" -").append(this.enunciado).append("\t");
        for (Opciones x: opciones){
            sb.append(x.toString()).append("\n");
        }

        return "Preguntas{" +
                "ID=" + ID +
                ", enunciado='" + enunciado + '\n' +
                ", opciones= '" + opciones + '\n' +
                '}';
    }
}
