package org.example.excepciones;

public class ExcepcionRepositorio extends RuntimeException {

    public ExcepcionRepositorio(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

