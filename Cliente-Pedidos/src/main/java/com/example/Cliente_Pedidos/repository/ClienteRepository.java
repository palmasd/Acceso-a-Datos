package com.example.Cliente_Pedidos.repository;

import com.example.Cliente_Pedidos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio para la entidad Cliente
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aquí puedes definir métodos personalizados si los necesitas
    Cliente findByNombre(String nombre); // Ejemplo: buscar un cliente por su nombre
}
