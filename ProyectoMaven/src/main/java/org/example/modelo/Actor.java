package org.example.modelo;

public class Actor {

    private int id;
    private String nombre;
    private int edad;
    private String personaje;

    public Actor(int id, String nombre, int edad, String personaje) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.personaje = personaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", personaje='" + personaje + '\'' +
                '}';
    }
}
