package vista;

import java.util.Scanner;

public class Escaner {

    static Scanner sc = new Scanner(System.in);

    public static String pedirString(String frase) {
        Consola.mostrarString(frase);
        return sc.nextLine();
    }

    public static int leerEntero() {
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    public static String pedirUsuario(String usuario){
        return pedirString(usuario);
    }

}
