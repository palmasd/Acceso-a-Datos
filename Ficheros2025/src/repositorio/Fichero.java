package repositorio;

import vista.Consola;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Fichero {

    private String ruta;

    public Fichero(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Fichero{" +
                "ruta='" + ruta + '\'' +
                '}';
    }

    public void AddDato(String dato) {
        //añadir la linea al fichero de texto
        ruta = "Ficheros2025/datos/datos.txt";
        FileWriter fichero = null;

        try {
            fichero = new FileWriter("Ficheros2025/datos/datos.txt", true);
            fichero.write(dato + "\n");
        } catch (IOException e) {
            //throw new RuntimeException();
            Consola.mostrarString("el Fichero no se pudo añadir");
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (IOException e) {
                Consola.mostrarString("Error");
            }

        }

    }

    public List<String> leerFichero() {
        List<String> listaCadena = new ArrayList<>();
        ruta = "Ficheros2025/datos/datos.txt";
        String cadena = "";
        FileReader fichero = null;
        BufferedReader lector = null;

        try {
            fichero = new FileReader(ruta);
            lector = new BufferedReader(fichero);

            do {
                cadena = lector.readLine();
                if (cadena != null) { //si no esta vacio ->
                    listaCadena.add(cadena); //una vez tenemos el String de Incidencia lo metemos a la ListaIncidencia en un arrayList
                }
            } while (cadena != null);//lee hasta que no haya nada mas que leer

        } catch (FileNotFoundException e) {
            Consola.mostrarString("Error no se encontro el fichero"); //usar consola.mostrar
        } catch (IOException e) {
            Consola.mostrarString("No pudo leer el fichero");
        } catch (Exception e) {
            Consola.mostrarString("Error inesperado");
        } finally {
            try {
                if (lector != null) {
                    lector.close();
                }
                if (fichero != null) {
                    fichero.close();
                }
            } catch (IOException e) {
                System.out.println("error");
            }
        }
        return listaCadena; // devolvemos la lista para que el servicio pueda usarla
    }
}
