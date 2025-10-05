package vista;

public class Consola {

    public static void mostrarString(String mensaje) {
        System.out.println(mensaje);

    }

    public static void mostrarMenu() {
        System.out.println("====Menu Principal====\n" +
                "1. añadir Incidencia\n" +
                "2. Buscar por Usuario\n" +
                "3. Buscar por Rango de Fechas\n" +
                "4. Salir\n");

    }

}
