package org.example.consola;

import java.util.Scanner;

public class Escaner {

    static Scanner sc = new Scanner(System.in);

    public static String pedirString(String frase) {
        Consola.mostrarString(frase);
        return sc.nextLine();
    }

    public static int leerEntero(String mensaje) {
        Consola.mostrarString(mensaje);
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    public static void limpiarBuffer(){
        sc.nextLine();
    }

}
