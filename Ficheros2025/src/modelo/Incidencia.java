package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Incidencia {

private String usuario;
private LocalDateTime fecha;
private String excepcion;

    public Incidencia(String usuario, LocalDateTime fecha, String excepcion) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.excepcion = excepcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFecha(){
        return fecha;
    }

    public void setFecha(LocalDateTime fecha){
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
                ", fecha='" + fecha + '\'' +
                ", excepcion='" + excepcion + '\'' +
                '}';
    }

}

