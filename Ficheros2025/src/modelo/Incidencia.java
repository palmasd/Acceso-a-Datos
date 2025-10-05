package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

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

    public static Incidencia aObjeto(String cadena){
        String[] dividido = cadena.split(";");

        String fechaHoraStr = dividido[0] + " " + dividido[1];
        DateTimeFormatter fechaFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, fechaFormato);

        //Usuario-fecha/hora-Excepcion
        return new Incidencia(dividido[2], fechaHora, dividido[3]);
    }

    @Override
    public String toString() {
        return "Incidencia{" +
                ", usuario='" + usuario + '\'' +
                ", fecha='" + fecha + '\'' +
                ", exception='" + excepcion + '\'' +
                '}';
    }

}

