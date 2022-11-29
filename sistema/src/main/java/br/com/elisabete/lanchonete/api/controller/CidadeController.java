package br.com.elisabete.lanchonete.api.controller;


import br.com.elisabete.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.elisabete.lanchonete.modelos.Cidade;
import br.com.elisabete.lanchonete.repositorios.CidadeRepository;
import br.com.elisabete.lanchonete.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Cidade findById(@PathVariable Long cidadeId){
        return cidadeRepository.findById(cidadeId).orElseThrow(()-> new EntidadeNaoEncontradaException("Cliente não encontrado na busca po id"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade save (@RequestBody Cidade cidade) {
        return cidadeService.salvar(cidade);
    }
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable Long cidadeId){
        cidadeService.remover(cidadeId);
    }


}
