package com.example.Cliente_Pedidos.service;

import com.example.Cliente_Pedidos.model.Cliente;
import java.util.List;
import java.util.Optional;

// Definimos la interfaz del servicio para Cliente
public interface ClienteService {

    List<Cliente> obtenerTodosLosClientes();

    Optional<Cliente> obtenerClientePorId(Long id);

    Cliente guardarCliente(Cliente cliente);

    void actualizarCliente(Cliente cliente);

    void eliminarCliente(Long id);
}
