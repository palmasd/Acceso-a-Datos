package vista;

import java.util.Scanner;
import java.util.function.Supplier;

public class Escaner {

    static Scanner sc = new Scanner(System.in);

    public static <T> T pedirDato(String dato, Supplier<T> lector){
        Consola.mostrarString(dato); //llamo al metodo consola para mostrar el dato que he pedido con el Escaner
        //implementar el scanner
        return lector.get();
    }

    public static String pedirString(String frase){
        return pedirDato(frase, () -> sc.nextLine());
    }

    public static int leerEntero(){
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

}
