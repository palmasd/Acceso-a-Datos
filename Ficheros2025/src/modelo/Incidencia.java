package modelo;

import utils.FormatoFecha;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Incidencia {

    private LocalDateTime fecha;
    private String excepcion;
    private String usuario;

    public Incidencia(String excepcion, LocalDateTime fecha, String usuario) {
        this.fecha = fecha;
        this.excepcion = excepcion;
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getExcepcion() {
        return excepcion;
    }

    public void setExcepcion(String excepcion) {
        this.excepcion = excepcion;
    }


    @Override
    public String toString() {
        return "Incidencia{" +
                ", usuario='" + usuario + '\'' +
                ", fecha='" + FormatoFecha.parsearFechaHora(fecha) + '\'' + //formato correcto en el cual leera la fecha del LocalDateTime
                ", exception='" + excepcion + '\'' +
                '}';
    }

}

