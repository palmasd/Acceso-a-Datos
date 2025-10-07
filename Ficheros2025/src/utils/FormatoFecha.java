package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatoFecha {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //formato para leer solo fecha
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm:ss");  //formato solo para leer hora
    public static final DateTimeFormatter FORMATO_COMPLETO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //formato para leer ambas

//metodos para utilizar cada uno de los formatos

    public static String formatearFecha(LocalDateTime fecha) {
        return fecha.format(FORMATO_FECHA);
    }

    public static String formatearHora(LocalDateTime fecha) {
        return fecha.format(FORMATO_HORA);
    }

    public static LocalDateTime parsearFechaHora(String fechaHoraStr) {
        return LocalDateTime.parse(fechaHoraStr, FORMATO_COMPLETO);
    }

    public static String parsearFechaHora(LocalDateTime fechaHoraStr) {
        return fechaHoraStr.format(FORMATO_COMPLETO);
    }

}
