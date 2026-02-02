package com.example.Cliente_Pedidos.model;

import jakarta.persistence.*;
// Esta clase representa la tabla "pedido" en la base de datos

@Entity
public class Pedido {
    // ID autogenerado como clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    // Relación N:1 -> Muchos Pedidos pertenecen a un Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Constructor vacío necesario para JPA
    public Pedido() {
    }

    // Constructor con parámetros
    public Pedido(String titulo, String descripcion, Cliente cliente) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.cliente = cliente;
    }

    // Getters y Setters (métodos de acceso y modificación)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
