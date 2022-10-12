package br.com.elisabete.lanchonete.api.controller;

import br.com.elisabete.lanchonete.Cliente;
import br.com.elisabete.lanchonete.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

    @RestController
    @RequestMapping("/clientes")
    public class ClienteController {
        @Autowired
        private ClienteRepository clienteRepository;
        @GetMapping
        public List<Cliente> listar(){
            return clienteRepository.listar();
        }

        @GetMapping("/{clienteId}")
        public Cliente buscar(@PathVariable Long clienteId){
            return clienteRepository.buscar(clienteId);
        }
    }


