package servicio;

import model.ListaPregunta;
import model.Opciones;
import model.Preguntas;
import repositorio.Fichero;

import java.util.ArrayList;
import java.util.List;

public class ServicioFichero {

    private final Fichero miFichero;

    public ServicioFichero(String ruta) {
        this.miFichero = new Fichero(ruta);
    }

    public List<Preguntas> parseoListaPregunta() {

        Preguntas miPregunta = null;
        int numero = 0;
        String texto = "";

        Opciones miOpcion = null;
        String caracter;
        String enunciado;
        boolean esCorrecta;
        String respuesta = "";

        List<Opciones> listaOpciones;
        ListaPregunta listaPreguntas = new ListaPregunta();
        List<String> listaStringFicheros;

        listaStringFicheros = miFichero.leerFichero();

        for (int i = 0; i< listaStringFicheros.size(); i++) {
            if (!listaStringFicheros.get(i).isEmpty()) {            //ignora todas las lineas vacias que hay entre pregunta y pregunta
                if (Character.isDigit(listaStringFicheros.get(i).charAt(0))) {//si el primer caracter de la primera linea es un digito entonces entra
                    listaOpciones = new ArrayList<>();              //reiniciar la lista de opciones cada vez que se accede a otra pregunta la
                    numero = Integer.parseInt(listaStringFicheros.get(i).substring(0, listaStringFicheros.get(i).indexOf("."))); //almacenar el numero desde la posicion 0 hasta el .
                    texto = listaStringFicheros.get(i).substring(listaStringFicheros.get(i).indexOf(".") + 2); //almacenar el texto de la primera linea desde el . +2 hasta el final
                    i++;    //aumentamos la i para pasar de linea

                    //bucle para recorrer las opciones de cada linea
                    while (i < listaStringFicheros.size() && Character.isUpperCase(listaStringFicheros.get(i).charAt(0)) && listaStringFicheros.get(i).charAt(1) == '.') {// lee hasta que el tamaño de i sea menor que el tamñao del fichero && que el primer caracter sea MAYUS en la posicion(0) y que en la posicion(1) sea un "."

                        caracter = listaStringFicheros.get(i).substring(0, listaStringFicheros.get(i).indexOf("."));//añadimos el caracter desde 0 hasta el .
                        enunciado = listaStringFicheros.get(i).substring(listaStringFicheros.get(i).indexOf(".") + 2);// añadimos el enunciado desde el . +2 para leer el enunciado

                        listaOpciones.add(new Opciones(caracter, enunciado, false)); //añado los obejtos parseados de Opciones a una lista de Opciones
                        i++;
                    }

                    respuesta = listaStringFicheros.get(i).substring(listaStringFicheros.get(i).indexOf(":") + 2); // añadimos la linea de respuesta desde : +2 hasta leer la respuesta y almacenarla
                    for (Opciones op : listaOpciones) { //iteramos la listaOpciones para cambiar esCorrecta si la respuesta es igual a uno de los caracteres que hay en las opciones
                        op.setEsCorrecta(op.getEtiqueta().equalsIgnoreCase(respuesta));
                    }

                    listaPreguntas.getListaPreguntas().add(new Preguntas(numero, texto, listaOpciones)); //cogemos la lista de ListaPreguntas para almacenar las preguntas y cada una de esas preguntas a su vez almacena una lista de opciones como atributo que tiene
            }
        }
    }
        return listaPreguntas.getListaPreguntas();
    }
}

