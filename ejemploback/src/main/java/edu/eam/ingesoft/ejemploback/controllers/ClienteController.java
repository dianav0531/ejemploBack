package edu.eam.ingesoft.ejemploback.controllers;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//api rest....
@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/customers")
    public void crear(@RequestBody Cliente cliente) {
        clienteService.crearCliente(cliente);
    }

    @GetMapping("/customers/{id}")
    public Cliente buscarCliente(@PathVariable("id") String cedula) {
        return clienteService.buscarCliente(cedula);
    }

    @GetMapping("/customers")
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @PutMapping("/customers")
    public Cliente editar(@RequestBody Cliente cliente) {
        return clienteService.editarCliente(cliente);
    }

    @DeleteMapping("/customers/{cedula}")
    public void borrarCliente(@PathVariable String cedula) {
        clienteService.borrarCliente(cedula);
    }
}
