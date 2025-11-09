package model;

public class Opciones {

    private int ID;
    private String etiqueta;
    private String texto;
    private boolean esCorrecta;

    public Opciones(String etiqueta, String texto, boolean esCorrecta) {
        this.etiqueta = etiqueta;
        this.texto = texto;
        this.esCorrecta = esCorrecta;
    }

    public void setID(int id){
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    @Override
    public String toString() {
        return "Opciones{" +
                "ID=" + ID +
                ", etiqueta='" + etiqueta + '\'' +
                ", texto='" + texto + '\'' +
                ", esCorrecta=" + esCorrecta +
                '}';
    }

}
