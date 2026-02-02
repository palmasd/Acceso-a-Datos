package com.example.Cliente_Pedidos.model;


import jakarta.persistence.*;

import java.util.List;
// Esta clase representa la tabla "alumno" en la base de datos

@Entity
public class Cliente {
    // ID autogenerado como clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    // Relación 1:N -> Un Cliente tiene muchos Trabajos
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    // Constructor vacío necesario para JPA
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y Setters (métodos de acceso y modificación)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pedido> getTrabajos() {
        return pedidos;
    }

    public void setTrabajos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
