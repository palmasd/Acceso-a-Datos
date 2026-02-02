package com.example.Cliente_Pedidos.controller;

import com.example.Cliente_Pedidos.model.Cliente;
import com.example.Cliente_Pedidos.model.Pedido;
import com.example.Cliente_Pedidos.service.ClienteService;
import com.example.Cliente_Pedidos.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;

    public PedidoController(PedidoService pedidoService, ClienteService clienteService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
    }

    @GetMapping("/pedidos")
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoService.obtenerTodosLosPedidos());
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());  // Pasamos la lista de clientes a la vista
        return "pedidos";
    }

    @PostMapping("/pedidos/guardar")
    public String guardarPedido(@RequestParam String titulo, @RequestParam String descripcion, @RequestParam Long clienteId) {
        Optional<Cliente> clienteOptional = clienteService.obtenerClientePorId(clienteId);  // Buscamos el cliente por ID

        if (clienteOptional.isPresent()) {
            Pedido pedido = new Pedido(titulo, descripcion, clienteOptional.get());  // Pasamos el cliente como tercer argumento
            pedidoService.guardarPedido(pedido);
        }
        return "redirect:/pedidos";  // Redirige a la lista de pedidos
    }

    @PostMapping("/pedidos/actualizar")
    public String actualizarPedido(@RequestParam Long id, @RequestParam String titulo, @RequestParam String descripcion){

        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setTitulo(titulo);
        pedido.setDescripcion(descripcion);

        pedidoService.actualizarPedido(pedido);
        return "redirect:/clientes";
    }

    @GetMapping("/pedidos/eliminar")
    public String eliminarPedido(@RequestParam Long id) {
        pedidoService.eliminarPedido(id);
        return "redirect:/pedidos";
    }
}
