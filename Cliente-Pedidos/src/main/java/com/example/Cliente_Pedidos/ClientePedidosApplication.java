package com.example.Cliente_Pedidos;

import com.example.Cliente_Pedidos.model.Cliente;
import com.example.Cliente_Pedidos.model.Pedido;
import com.example.Cliente_Pedidos.service.ClienteService;
import com.example.Cliente_Pedidos.service.PedidoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class ClientePedidosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientePedidosApplication.class, args);
    }

    @Bean
    public CommandLineRunner datosDePrueba(ClienteService clienteService, PedidoService pedidoService) {
        return args -> {
            // 1. Crear y guardar clientes
            Cliente cliente1 = new Cliente("Peter Parker", "peterp@empresa.com");
            Cliente cliente2 = new Cliente("Mary Jane Watson", "maryjanew@empresa.com");
            clienteService.guardarCliente(cliente1);
            clienteService.guardarCliente(cliente2);

            // 2. Crear y guardar pedidos asociados a los clientes
            Pedido pedido1 = new Pedido("Pedido de Telaraña", "Telaraña artificial", cliente1);
            Pedido pedido2 = new Pedido("Pedido de Balanceo", "Estudio del balanceo", cliente1);
            Pedido pedido3 = new Pedido("Pedido de Portadas", "Portadas históricas de DB", cliente2);
            pedidoService.guardarPedido(pedido1);
            pedidoService.guardarPedido(pedido2);
            pedidoService.guardarPedido(pedido3);

            // 3. Mostrar todos los clientes
            System.out.println("Lista de clientes:");
            clienteService.obtenerTodosLosClientes().forEach(cliente -> {
                System.out.println("Cliente: " + cliente.getNombre() + ", Email: " + cliente.getEmail());
            });

            // 4. Mostrar todos los pedidos
            System.out.println("Lista de pedidos:");
            pedidoService.obtenerTodosLosPedidos().forEach(pedido -> {
                System.out.println("Pedido: " + pedido.getTitulo() + ", Cliente: " + pedido.getCliente().getNombre());
            });

            // 5. Buscar pedidos por título
            System.out.println("Pedidos que contienen 'Balanceo':");
            pedidoService.buscarPorTitulo("Balanceo").forEach(pedido -> {
                System.out.println("Pedido: " + pedido.getTitulo());
            });

            // 6. Obtener cliente por ID (con Optional)
            Optional<Cliente> clienteEncontrado = clienteService.obtenerClientePorId(1L);
            if (clienteEncontrado.isPresent()) {
                System.out.println("Cliente encontrado: " + clienteEncontrado.get().getNombre());
            } else {
                System.out.println("Cliente no encontrado.");
            }
        };
    }
}
