package vista;

import java.util.Scanner;
import java.util.function.Supplier;

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

}
