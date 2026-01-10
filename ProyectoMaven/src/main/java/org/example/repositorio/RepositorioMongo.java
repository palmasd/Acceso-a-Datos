package org.example.repositorio;

//TODO: imports de * no tiene que haber

import com.mongodb.MongoClientSettings;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.example.modelo.Actor;
import org.example.modelo.Pelicula;
import org.example.servicio.IRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

            String mongoUri = props.getProperty("db.mongo.uri", "mongodb://localhost:27017");
            String dbName = props.getProperty("db.mongo.name", "cine");

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
    public void guardar(Pelicula p) {
        //todo: metodo para guardar una lista de Peliculas
        Document doc = new Document()
                .append("id", p.getId())
                .append("titulo", p.getTitulo())
                .append("duracion", p.getDuracion())
                .append("listaActores", p.getListaActores());
        collection.insertOne(doc);
    }

    //metodo para guardarList(ArrayList<Peliculas> listaPeliculas){}

    // Devuelve todas las películas
    public List<Pelicula> listar() {
        List<Pelicula> lista = new ArrayList<>();
        FindIterable<Document> docs = collection.find(); //parametro en el find
        for (Document doc : docs) {
            lista.add(new Pelicula(
                    doc.getInteger("id"),
                    doc.getString("titulo"),
                    doc.getInteger("duracion"),
                    doc.getList("listaActores", Actor.class)
            ));
        }
        return lista;
    }

    // Actualiza una película por id
    public void actualizar(Pelicula p) {
        collection.updateOne(
                Filters.eq("id", p.getId()),
                Updates.combine(
                        Updates.set("titulo", p.getTitulo()),
                        Updates.set("duracion", p.getDuracion()),
                        Updates.set("listaActores", p.getListaActores())
                )
        );
    }

    // Borra una película por id
    public void borrar(int id) {
        collection.deleteOne(Filters.eq("id", id));
    }
}

