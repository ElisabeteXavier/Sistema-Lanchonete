package br.com.elisabete.lanchonete.api.controller;
import br.com.elisabete.lanchonete.modelos.Estado;
import br.com.elisabete.lanchonete.repositorios.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estado")

public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.listar();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId){
        return estadoRepository.buscar(estadoId);
    }
}
