package repositorio;

import Excepciones.ExcepcionPersonalizada;
import modelo.Incidencia;
import vista.Consola;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

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

    //sobrecarga de metodos pero cada uno con distintos parametros.
    //Hacer los metodos generales para la reutilizacion de codigo a posterior


    public  void AddDato(String dato){
        //añadir la linea al fichero de texto
        ruta = "datos/datos.txt";
        FileWriter fichero= null;

        try{
            fichero = new FileWriter(ruta, true);
            fichero.write(dato + "\n");
        }catch (IOException e){
            //throw new RuntimeException();
            Consola.mostrarString("el Fichero no se pudo añadir");
        } finally {
            try {
                if (fichero!=null){
                    fichero.close();
                }
            } catch (IOException e) {
                Consola.mostrarString("Error");
            }

        }

    }


    public void leerFichero(String [] dato) {
        ruta = "datos/datos.txt";
        String cadena = "";
        FileReader fichero = null;
        BufferedReader lector = null;

        try{
            fichero = new FileReader(ruta);
            lector = new BufferedReader(fichero);

            do{
                cadena = lector.readLine();
                if (cadena!=null){
                    Consola.mostrarString(cadena);
                }
            }while(cadena!=null);

        }catch (FileNotFoundException e) {
            Consola.mostrarString("Error no se encontro el fichero"); //usar consola.mostrar
        }catch (IOException e){
            Consola.mostrarString("No pudo leer el fichero");
        } catch (Exception e) {
            Consola.mostrarString("Error inesperado");
        } finally {
            try {
                if (lector!=null){
                    lector.close();
                }
                if (fichero!=null){
                    fichero.close();
                }
            } catch (IOException e){
                System.out.println("error");
            }
        }

    }


//    public static ArrayList<String> leerFichero(String dato){
//
//        return "";
//    }

//    public static String buscarDAto(LocalDateTime fechaInicio, LocalDateTime fechaFinal){
//
//        return "";
//    }


}
