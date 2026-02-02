package com.example.Cliente_Pedidos.repository;

import com.example.Cliente_Pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Repositorio para la entidad Pedido
@Repository
public interface PedidoReposiroty extends JpaRepository<Pedido, Long> {
    // Ejemplo de método personalizado: buscar pedidos por su título
    List<Pedido> findByTituloContaining(String titulo);
}
