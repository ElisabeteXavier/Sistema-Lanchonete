package br.com.elisabete.lanchonete.api.controller;

import br.com.elisabete.lanchonete.modelos.Cliente;
import br.com.elisabete.lanchonete.repositorios.ClienteRepository;
import br.com.elisabete.lanchonete.service.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public Cliente findById(@PathVariable Long clienteId) {
        return clienteRepository.findById(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long clienteId) {
        clienteService.remover(clienteId);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,@RequestBody Cliente cliente) {
        Cliente clienteAtual = clienteRepository.findById(clienteId);
        if (clienteAtual != null) {
            BeanUtils.copyProperties(cliente, clienteAtual, "id");
            Cliente clienteSalva= clienteService.salvar(clienteAtual);
            return ResponseEntity.ok(clienteSalva);
        }
        return ResponseEntity.notFound().build();
    }
}


