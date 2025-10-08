package Excepciones;

import vista.Escaner;

public class ValidarUsuario {


    public static String UsuarioValido(String mensaje) throws ExcepcionPersonalizada {
        String usuario;
        usuario = Escaner.pedirUsuario(mensaje);

        if (usuario == null || usuario.trim().isEmpty()) {
            throw new ExcepcionPersonalizada("El usuario no puede ser nulo, ni tener espacios, ni estar vacio");
        }

        if (!usuario.matches("^[a-zA-Z]+$")) {
            throw new ExcepcionPersonalizada("El usuario debe tener letras de la -aA- a la -zZ-");
        }

        return usuario;
    }
}
