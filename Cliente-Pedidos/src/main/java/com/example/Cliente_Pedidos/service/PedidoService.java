package com.example.Cliente_Pedidos.service;

import com.example.Cliente_Pedidos.model.Pedido;
import java.util.List;
import java.util.Optional;

// Definimos la interfaz del servicio para Pedido
public interface PedidoService {

    List<Pedido> obtenerTodosLosPedidos();

    Optional<Pedido> obtenerPedidoPorId(Long id);

    Pedido guardarPedido(Pedido pedido);

    void actualizarPedido(Pedido pedido);

    void eliminarPedido(Long id);

    List<Pedido> buscarPorTitulo(String titulo); // Método adicional para búsquedas personalizadas
}
