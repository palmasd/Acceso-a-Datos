package org.example.repositorio;

//TODO: imports de * no tiene que haber

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.example.excepciones.ExcepcionRepositorio;
import org.example.modelo.Actor;
import org.example.modelo.Pelicula;
import org.example.servicio.IRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositorioMongo implements IRepositorio {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public RepositorioMongo() {
        try {
            // Cargamos propiedades desde application.properties
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

            String mongoUri = props.getProperty("db.mongo.uri");
            String dbName = props.getProperty("db.mongo.name");

            // Creamos la conexión con MongoDB
            mongoClient = MongoClients.create(mongoUri);
            database = mongoClient.getDatabase(dbName);

            // Colección donde almacenaremos películas
            collection = database.getCollection("peliculas");

            //todo: en el constructor, para cambiar lsa excepciones
        } catch (Exception e) {
            System.out.println("Error configurando MongoDB: " + e.getMessage());
        }
    }


    //todo: try/catch en todos los metodos
    // Inserta una película
    //todo: metodo para guardar una lista de Peliculas
    public void guardar(Pelicula p) {
        try {
            List<Document> actoresDoc = new ArrayList<>();
            for (Actor x : p.getListaActores()) {
                actoresDoc.add(new Document()
                        .append("id", x.getId())
                        .append("nombre", x.getNombre())
                        .append("edad", x.getEdad())
                        .append("personaje", x.getPersonaje())
                );
            }

            Document peliculaDoc = new Document()
                    .append("id", p.getId())
                    .append("titulo", p.getTitulo())
                    .append("duracion", p.getDuracion())
                    .append("listaActores", actoresDoc);
            collection.insertOne(peliculaDoc);

        } catch (Exception e) {
            throw new ExcepcionRepositorio("Error al guardar la pelicula" + p.getId(), e);
        }
    }

    //metodo para guardarList(ArrayList<Peliculas> listaPeliculas){}

    // Devuelve todas las películas
    public List<Pelicula> listar() {
        Pelicula pelicula;
        Actor actor;
        int id;
        String titulo;
        int duracion;
        int idActor;
        String nombre;
        int edad;
        String personaje;
        List<Actor> listaActores;

        try {
            List<Pelicula> lista = new ArrayList<>();
            List<Document> listaDocActores;
            FindIterable<Document> docs = collection.find(); //parametro en el find

            for (Document doc : docs) {

                id = ((Number)doc.get("id")).intValue();
                titulo = doc.getString("titulo");
                duracion = ((Number)doc.get("duracion")).intValue();
                listaDocActores = doc.getList("listaActores", Document.class);

                listaActores = new ArrayList<>();
                if (listaDocActores != null) {
                    for (Document x : listaDocActores) {
                        idActor = ((Number)x.get("id")).intValue();
                        nombre = x.getString("nombre");
                        edad = ((Number)x.get("edad")).intValue();
                        personaje = x.getString("personaje");
                        listaActores.add(actor = new Actor(idActor, nombre, edad, personaje));
                    }
                }

                lista.add(pelicula = new Pelicula(id, titulo, duracion, listaActores));

            }
            return lista;

        } catch (Exception e) {
            throw new ExcepcionRepositorio("Error al listar peliculas", e);
        }
    }

    // Actualiza una película por id
    public void actualizar(Pelicula p) {

        try {
            List<Document> actores = new ArrayList<>();
            for (Actor x : p.getListaActores()) {
                actores.add(new Document()
                        .append("id", x.getId())
                        .append("nombre", x.getNombre())
                        .append("edad", x.getEdad())
                        .append("personaje", x.getPersonaje())
                );
            }

            collection.updateOne(
                    Filters.eq("id", p.getId()),
                    Updates.combine(
                            Updates.set("titulo", p.getTitulo()),
                            Updates.set("duracion", p.getDuracion()),
                            Updates.set("listaActores", actores)
                    )
            );
        } catch (Exception e) {
            throw new ExcepcionRepositorio("Error al actualizar las peliculas", e);
        }
    }

    // Borra una película por id
    public void borrar(int id) {
        try {
            collection.deleteOne(Filters.eq("id", id));
        } catch (Exception e) {
            throw new ExcepcionRepositorio("Error al borrar la película con id " + id, e);
        }
    }
}

