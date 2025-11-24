package org.example.repositorio;


// Importación de la clase Gson, utilizada para convertir objetos Java en JSON y viceversa.
// REFERENCIA: https://github.com/google/gson
import com.google.gson.Gson;

// Importamos GsonBuilder, que permite configurar el comportamiento de Gson (como formato legible).
import com.google.gson.GsonBuilder;

// TypeToken permite capturar tipos genéricos en tiempo de ejecución (necesario para listas).
// Java solo “sabe” que es una List, no que contiene Pelicula.
// Esto provoca problemas cuando Gson intenta deserializar JSON
// REFERENCIA: https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
import com.google.gson.reflect.TypeToken;

import org.example.modelo.Pelicula;

import java.io.*; // OJO!!! Reader, Writer, FileReader, FileWriter, IOException.
import java.lang.reflect.Type; // Permite manipular tipos genéricos.
import java.util.ArrayList;
import java.util.List;
// ¿Excepciones...?

public class RepositorioJSON {

    // Ruta del archivo JSON en disco.
    private final String fichero;

    // Instancia de Gson utilizada para serializar/deserializar.
// Serializar significa convertir un objeto en memoria (Java, Python, etc.) en un formato que pueda ser almacenado o transmitido.
// Deserializar es el proceso inverso: convertir datos almacenados o recibidos en memoria en un objeto usable por el programa.
    private final Gson gson;

    // Tipo genérico que representa "List<Pelicula>" para poder deserializarlo correctamente.
    private final Type tipoListaPeliculas;

    // Constructor: recibe el nombre del fichero y lo crea si no existe.
    public RepositorioJSON(String fichero) {
        this.fichero = fichero;

// Configuración de Gson para que el archivo JSON sea legible (con saltos de línea y sangría).
// new Gson() funcionaría, pero produciría salida sin formato.
// Es interesante valorar esta función para archivos XML
        this.gson = new GsonBuilder().setPrettyPrinting().create();

// TypeToken captura el tipo real de List<Pelicula> evitando el borrado de tipos genéricos.
        this.tipoListaPeliculas = new TypeToken<List<Pelicula>>(){}.getType();

// Si el archivo JSON no existe, se crea uno vacío con una lista de películas vacía.
        File f = new File(fichero);
        if (!f.exists()) {
            guardarLista(new ArrayList<>()); // JSON inicial: []
        }
    }

    // Guarda una nueva película en el archivo JSON.
    public void guardar(Pelicula p) {
// Primero recuperamos la lista actual del archivo.
        List<Pelicula> lista = listar();

// Agregamos la nueva película a la lista.
        lista.add(p);

// Guardamos la lista completa en el archivo.
        guardarLista(lista);
    }

    // Devuelve todas las películas almacenadas en el archivo JSON.
    public List<Pelicula> listar() {
        try (Reader r = new FileReader(fichero)) {
// Deserializa el contenido del archivo a List<Pelicula>.
            List<Pelicula> lista = gson.fromJson(r, tipoListaPeliculas);

// Si el archivo está vacío o null, devolvemos una lista vacía.
            return lista != null ? lista : new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Actualiza una película existente, buscándola por su ID.
    public void actualizar(Pelicula p) {
        List<Pelicula> lista = listar();

// Buscar la película en la lista y reemplazarla.
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == p.getId()) {
// Reemplaza la película encontrada con la versión actualizada.
                lista.set(i, p);
                break; // ¿en serio?
            }
        }

// Guardamos la lista completa nuevamente.
        guardarLista(lista);
    }

    // Elimina una película del archivo JSON según su ID.
    public void borrar(int id) {
        List<Pelicula> lista = listar();

// Muy útil!! removeIf elimina elementos que cumplan la condición del predicado.
        lista.removeIf(p -> p.getId() == id);

        guardarLista(lista);
    }

    // Método privado que sobrescribe el archivo JSON con la lista proporcionada.
    private void guardarLista(List<Pelicula> lista) {
// Try-with-resources para asegurar el cierre automático del archivo.
        try (Writer w = new FileWriter(fichero)) {

// Serializa la lista a JSON y lo escribe en el archivo.
            gson.toJson(lista, w);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}