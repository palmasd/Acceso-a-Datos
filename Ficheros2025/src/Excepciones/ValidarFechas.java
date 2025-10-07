package Excepciones;

import utils.FormatoFecha;
import vista.Escaner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static utils.FormatoFecha.*;

public class ValidarFechas {

    public static String FechaValida(String mensaje) throws ExcepcionPersonalizada {
        String fecha;
        fecha = Escaner.pedirFecha(mensaje);

        if (fecha == null && fecha.isEmpty()){
            throw new ExcepcionPersonalizada("la fecha no puede ser nula, ni estar vacia");
        }
        if (!fecha.matches("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$")){
            throw new ExcepcionPersonalizada("Formato de fecha no valido: debe ser yyyy-MM-dd HH:mm:ss");
        }

        try {
            LocalDateTime.parse(fecha, FORMATO_COMPLETO);
        } catch (DateTimeException e) {
            throw new ExcepcionPersonalizada("La fecha introducida no existe o es incorrecta");
        }

        return fecha;
    }

}
