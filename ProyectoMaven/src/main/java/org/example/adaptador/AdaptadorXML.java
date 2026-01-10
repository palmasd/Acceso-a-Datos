package org.example.adaptador;

import org.example.modelo.Actor;
import org.example.modelo.Pelicula;
import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorXML {

    public AdaptadorXML(){

    }

    public Element peliculaToElement(Document doc, Pelicula p){

        Element pelicula = doc.createElement("pelicula");

        Element id = doc.createElement("id");
        id.setTextContent(String.valueOf(p.getId()));
        pelicula.appendChild(id);

        Element titulo = doc.createElement("titulo");
        titulo.setTextContent(p.getTitulo());
        pelicula.appendChild(titulo);

        Element duracion = doc.createElement("duracion");
        duracion.setTextContent(String.valueOf(p.getDuracion()));
        pelicula.appendChild(duracion);

        Element listaActores =  doc.createElement("actores");

        System.out.println("Actores en la película: " + p.getListaActores().size());
        for (Actor a: p.getListaActores()){
            Element actor = doc.createElement("actor");

            Element idActor = doc.createElement("id");
            idActor.setTextContent(String.valueOf(a.getId()));
            actor.appendChild(idActor);

            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(a.getNombre());
            actor.appendChild(nombre);

            Element edad = doc.createElement("edad");
            edad.setTextContent(String.valueOf(a.getEdad()));
            actor.appendChild(edad);

            Element personaje = doc.createElement("personaje");
            personaje.setTextContent(a.getPersonaje());
            actor.appendChild(personaje);

            listaActores.appendChild(actor);

        }
            pelicula.appendChild(listaActores);

        return pelicula;
    }


    public Pelicula elementToPelicula(Element e){
        int id;
        String titulo;
        int duracion;
        List<Actor> listaActores;
        int idActor;
        String nombre;
        int edad;
        String personaje;
        Element actorElem;


         id = Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent());
         titulo = e.getElementsByTagName("titulo").item(0).getTextContent();
         duracion = Integer.parseInt(e.getElementsByTagName("duraicon").item(0).getTextContent());

         listaActores = new ArrayList<>(); //creamos una lista para añadir los nodos
         NodeList actoresNodes = e.getElementsByTagName("actor"); //cogemos el NodeList para recorrer la lista de nodos  de actores
         for (int i = 0; i < actoresNodes.getLength(); i++) {
             actorElem = (Element) actoresNodes.item(i);//castear la lista de nodeList de actores para tenerlos como element

             idActor =Integer.parseInt(actorElem.getElementsByTagName("id").item(0).getTextContent());
            nombre = actorElem.getElementsByTagName("nombre").item(0).getTextContent();
            edad =Integer.parseInt(actorElem.getElementsByTagName("edad").item(0).getTextContent());
            personaje = actorElem.getElementsByTagName("personaje").item(0).getTextContent();

            listaActores.add(new Actor(idActor, nombre, edad, personaje)); //los añadimos a la lista de Actores para poder devovler la pelicula

        }

        return new Pelicula(id, titulo, duracion, listaActores);

    }

}



