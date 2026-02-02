package com.example.Cliente_Pedidos.service.impl;

import com.example.Cliente_Pedidos.model.Pedido;
import com.example.Cliente_Pedidos.repository.PedidoReposiroty;
import com.example.Cliente_Pedidos.service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoReposiroty pedidoRepository;

    // Inyección de dependencias a través del constructor
    public PedidoServiceImpl(PedidoReposiroty pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        // Aquí puedes añadir validaciones adicionales antes de guardar
        return pedidoRepository.save(pedido);
    }

    @Override
    public void actualizarPedido(Pedido pedido) {
       Optional<Pedido> pedidoExistente = pedidoRepository.findById(pedido.getId());

       if (pedidoExistente.isPresent()){
           Pedido p = pedidoExistente.get();
           p.setTitulo(pedido.getTitulo());
           p.setDescripcion(pedido.getDescripcion());

           pedidoRepository.save(p);
       }
    }

    @Override
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public List<Pedido> buscarPorTitulo(String titulo) {
        // Buscar pedidos cuyo título contenga el texto indicado (consulta personalizada)
        return pedidoRepository.findByTituloContaining(titulo);
    }
}
