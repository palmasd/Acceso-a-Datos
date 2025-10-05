package Excepciones;

import vista.Escaner;

public class ValidarUsuario {

    public static String pedirUsuarioValido() throws ExcepcionPersonalizada {
        String usuario;
        usuario = Escaner.pedirString("Escribe un Usuario");

        if (usuario == null) {
            throw new ExcepcionPersonalizada("El usuario no puede ser nulo");
        }
        if (usuario.isEmpty()) {
            throw new ExcepcionPersonalizada("El usuario no puede estar vacio");
        }
        if (!usuario.matches("^[a-zA-Z]+$")) {
            throw new ExcepcionPersonalizada("El usuario debe tener letras de la -aA- a la -zZ-");
        }

        return usuario;
    }
}
