package com.example.Cliente_Pedidos.service.impl;

import com.example.Cliente_Pedidos.model.Cliente;
import com.example.Cliente_Pedidos.repository.ClienteRepository;
import com.example.Cliente_Pedidos.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    // Inyección de dependencias a través del constructor
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        // Aquí podrías agregar validaciones o lógica adicional antes de guardar
        return clienteRepository.save(cliente);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(cliente.getId());
        if (clienteExistente.isPresent()){
            Cliente c = clienteExistente.get();
            c.setNombre(cliente.getNombre());
            c.setEmail(cliente.getEmail());
            clienteRepository.save(c);
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
