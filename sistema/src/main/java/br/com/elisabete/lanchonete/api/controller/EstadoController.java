package br.com.elisabete.lanchonete.api.controller;
import br.com.elisabete.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.elisabete.lanchonete.modelos.Estado;
import br.com.elisabete.lanchonete.repositorios.EstadoRepository;
import br.com.elisabete.lanchonete.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")

public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private EstadoService estadoService;
    @GetMapping
    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public Estado findById(@PathVariable Long estadoId){
        return estadoRepository.findById(estadoId).orElseThrow(()-> new EntidadeNaoEncontradaException("Estado não encontrado na busca po id"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado save (@RequestBody Estado estado) {
        return estadoService.salvar(estado);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable Long estadoId){
        estadoService.remover(estadoId);
    }


}
