package com.example.Cliente_Pedidos.controller;

import com.example.Cliente_Pedidos.model.Cliente;
import com.example.Cliente_Pedidos.service.ClienteService;
import org.springframework.stereotype.Controller; // Marca esta clase como controlador
import org.springframework.ui.Model; // Permite pasar datos desde el controlador a la vista
import org.springframework.web.bind.annotation.GetMapping; // Maneja solicitudes GET
import org.springframework.web.bind.annotation.PostMapping; // Maneja solicitudes POST
import org.springframework.web.bind.annotation.RequestParam; // Captura parámetros de la URL o formulario

@Controller // Indica que esta clase es un controlador web
public class ClienteController {
    private final ClienteService clienteService;

    // Constructor para inyectar el servicio de cliente
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Maneja la solicitud GET a /clientes
    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        // Agrega la lista de clientes como atributo del modelo para pasarla a la vista
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        // Retorna el nombre de la vista (clientes.html)
        return "clientes";
    }

    // Maneja la solicitud POST al formulario de /clientes/guardar
    @PostMapping("/clientes/guardar")
    public String guardarCliente(@RequestParam String nombre, @RequestParam String email) {
        // Crea un nuevo objeto Cliente con los datos del formulario y lo guarda en la base de datos
        Cliente cliente = new Cliente(nombre, email);
        clienteService.guardarCliente(cliente);
        // Redirige a la página de lista de clientes después de guardar
        return "redirect:/clientes";
    }


    @PostMapping("/clientes/actualizar")
    public String actualizarCliente(@RequestParam Long id, @RequestParam String nombre, @RequestParam String email) {

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNombre(nombre);
        cliente.setEmail(email);

        clienteService.actualizarCliente(cliente);
        return "redirect:/clientes";

    }

    // Maneja la solicitud GET para eliminar un cliente por ID
    @GetMapping("/clientes/eliminar")
    public String eliminarCliente(@RequestParam Long id) {
        // Llama al servicio para eliminar el cliente por su ID
        clienteService.eliminarCliente(id);
        // Redirige a la página de lista de clientes después de eliminar
        return "redirect:/clientes";
    }
}
