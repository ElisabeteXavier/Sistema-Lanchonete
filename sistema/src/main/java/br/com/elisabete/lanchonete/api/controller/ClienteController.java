package br.com.elisabete.lanchonete.api.controller;

import br.com.elisabete.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.elisabete.lanchonete.modelos.Cidade;
import br.com.elisabete.lanchonete.modelos.Cliente;
import br.com.elisabete.lanchonete.repositorios.ClienteRepository;
import br.com.elisabete.lanchonete.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
    public Cliente findById(@PathVariable String clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(()-> new EntidadeNaoEncontradaException("Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) {

        return clienteService.salvar(cliente);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String clienteId) {
        clienteService.remover(clienteId);
    }


    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente>atualizar(@PathVariable String clienteId, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteAtual = clienteRepository.findById(clienteId);
        if (clienteAtual.isPresent()) {
            BeanUtils.copyProperties(cliente, clienteAtual.get(), "cpf");
            Cliente clienteSalvo = clienteRepository.save(clienteAtual.get());
            return ResponseEntity.ok(clienteSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{clienteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable String clienteId, @RequestBody Map<String, Object> campos) {
        Optional<Cliente> clienteAtual = clienteRepository.findById(clienteId);

        if (clienteAtual.isPresent()) {
            merge(campos, clienteAtual.get());
            return atualizar(clienteId, clienteAtual.get());
        }

        return ResponseEntity.notFound().build();
    }

    private void merge(Map<String, Object> dadosOrigem, Cliente clienteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Cliente clienteOrigem = objectMapper.convertValue(dadosOrigem, Cliente.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade)
                -> {
            Field field = ReflectionUtils.findField(Cliente.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, clienteOrigem);
            ReflectionUtils.setField(field, clienteDestino, novoValor);
        });
    }

    @GetMapping("/email")
    private Cliente buscaEmail(String email){

      return  clienteRepository.findByEmail(email);

    }

    @GetMapping("/cidade")
    private Cliente buscaCidade(Cidade cidade){

        return  clienteRepository.findByCidade(cidade);

    }
}



