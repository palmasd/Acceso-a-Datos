package org.example.repositorio;

import org.example.modelo.Actor;
import org.example.modelo.Pelicula;
import org.example.servicio.IRepositorio;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RepositorioXML implements IRepositorio {

    private final File fichero;

    public RepositorioXML(String fichero) {
        this.fichero = new File(fichero);
        if (!this.fichero.exists()) {
            try {
                // DocumentBuilderFactory: fábrica para crear DocumentBuilder
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                // DocumentBuilder: parser para crear un Document (XML en memoria)
                DocumentBuilder builder = factory.newDocumentBuilder();

                // Crear documento XML vacío
                Document doc = builder.newDocument();
                // Crear nodo raíz <peliculas>
                Element root = doc.createElement("peliculas");
                doc.appendChild(root);

                // Guardar el documento en disco
                guardarDocumento(doc, this.fichero);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void guardar(Pelicula p) {
        try {
            Document doc = crearDocuemnto();

            Element root = doc.getDocumentElement();

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

            Element listaActores = doc.createElement("actores");

            System.out.println("Actores en la película: " + p.getListaActores().size());
            for (Actor a : p.getListaActores()) {
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
            root.appendChild(pelicula); //insertamos correctamente la <pelicula> dentro del XML
            // Guardar XML actualizado con buena indentación
            guardarDocumento(doc, fichero);
        } catch (Exception e) {
            e.printStackTrace(); //todo meter Excepciones en todos
        }
    }

    public List<Pelicula> listar() {
        List<Pelicula> lista = new ArrayList<>();
        int id;
        String titulo;
        int duracion;
        List<Actor> listaActores;
        int idActor;
        String nombre;
        int edad;
        String personaje;
        Element e = null;
        Element actorElem;

        Document doc = crearDocuemnto();

        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int j = 0; j < peliculas.getLength(); j++) {
            e = (Element) peliculas.item(j);

            id = Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent());
            titulo = e.getElementsByTagName("titulo").item(0).getTextContent();
            duracion = Integer.parseInt(e.getElementsByTagName("duracion").item(0).getTextContent());

            listaActores = new ArrayList<>(); //creamos una lista para añadir los nodos
            NodeList actoresNodes = e.getElementsByTagName("actor"); //cogemos el NodeList para recorrer la lista de nodos  de actores
            for (int i = 0; i < actoresNodes.getLength(); i++) {
                actorElem = (Element) actoresNodes.item(i);//castear la lista de nodeList de actores para tenerlos como element

                idActor = Integer.parseInt(actorElem.getElementsByTagName("id").item(0).getTextContent());
                nombre = actorElem.getElementsByTagName("nombre").item(0).getTextContent();
                edad = Integer.parseInt(actorElem.getElementsByTagName("edad").item(0).getTextContent());
                personaje = actorElem.getElementsByTagName("personaje").item(0).getTextContent();

                listaActores.add(new Actor(idActor, nombre, edad, personaje)); //los añadimos a la lista de Actores para poder devovler la pelicula

            }

            lista.add(new Pelicula(id, titulo, duracion, listaActores));
        }

        return lista;

    }

    public void actualizar(Pelicula p) {
        int idActual;
        int idActualActor;

        NodeList peliculasNode;
        NodeList actoresNode;
        Node nodePelicula;


        try {

            Document doc = crearDocuemnto();

            peliculasNode = doc.getElementsByTagName("pelicula"); //en el nodeList almaceno lo que tengo en el doc

            for (int i = 0; i < peliculasNode.getLength(); i++) {
                    nodePelicula = peliculasNode.item(i);

                if (nodePelicula.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementPelicula = (Element) nodePelicula;
                    //COGEMOS el id de la pelicula actual del XML

                    idActual = Integer.parseInt(elementPelicula.getElementsByTagName("id").item(0).getTextContent());

                    //si los ids coinciden, entonces actualizamos las etiquetas
                    if (idActual == p.getId()) {
                        if (p.getTitulo() != null && !p.getTitulo().isEmpty()) {
                            Node tituloNode = elementPelicula.getElementsByTagName("titulo").item(0);
                            if (tituloNode != null) {
                                tituloNode.setTextContent(p.getTitulo());
                            }
                        }

                        if (p.getDuracion() != 0) {
                            Node duracionNode = elementPelicula.getElementsByTagName("duracion").item(0);
                            if (duracionNode != null) {
                                duracionNode.setTextContent(String.valueOf(p.getDuracion()));
                            }
                        }
                    }
                    //ahora pillamos los actores de la peliculas actual
                    actoresNode = elementPelicula.getElementsByTagName("actores");

                    //recorremos los actores actuales de la pelicula actual
                    for (int j = 0; j < actoresNode.getLength(); j++) {
                        Node actorNode = actoresNode.item(j);
                        if (actorNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element actorElement = (Element) actorNode;
                            //obtenemos su id
                            idActualActor = Integer.parseInt(actorElement.getElementsByTagName("id").item(0).getTextContent());
                            for (Actor a : p.getListaActores()) {

                                //si coincide el id del actor actual con el id del actor nuevo, actualizamos sus campos
                                if (idActualActor == a.getId()) {
                                    actorElement.getElementsByTagName("nombre").item(0).setTextContent(a.getNombre());
                                    actorElement.getElementsByTagName("edad").item(0).setTextContent(String.valueOf(a.getEdad()));
                                    actorElement.getElementsByTagName("personaje").item(0).setTextContent(a.getPersonaje());

                                }
                            }
                        }
                    }
                }

            }

            guardarDocumento(doc, fichero);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrar(int id) {
        try {
            Document doc = crearDocuemnto();

            NodeList peliculasNode = doc.getElementsByTagName("pelicula");

            for (int i = 0; i < peliculasNode.getLength(); i++) {
                Node node = peliculasNode.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    int idActual = Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent());
                    if (idActual == id) {
                        e.getParentNode().removeChild(e);
                    }
                }
            }

            guardarDocumento(doc, fichero);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Document crearDocuemnto() {
        Document doc = null;
        DocumentBuilder builder = null;
        DocumentBuilderFactory factory = null;


        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();

            if (!fichero.exists()) {
                // Crear XML inicial
                doc = builder.newDocument();
                Element root = doc.createElement("peliculas");
                doc.appendChild(root);

                guardarDocumento(doc, fichero);
            } else {
                // Cargar el existente
                doc = builder.parse(fichero);
                doc.getDocumentElement().normalize();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

    private void limpiarNodosTextoVacios(Node node) {
        NodeList hijos = node.getChildNodes();

        for (int i = hijos.getLength() - 1; i >= 0; i--) {
            Node hijo = hijos.item(i);

            if (hijo.getNodeType() == Node.TEXT_NODE &&
                    hijo.getTextContent().trim().isEmpty()) {
                node.removeChild(hijo);
            }
            else if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                limpiarNodosTextoVacios(hijo);
            }
        }
    }


    private void guardarDocumento(Document doc, File f) throws TransformerException {

       limpiarNodosTextoVacios(doc);

        doc.normalizeDocument(); // Limpia texto basura y nodos vacíos

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Configuración para XML legible
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(new DOMSource(doc), new StreamResult(f));
    }
}


