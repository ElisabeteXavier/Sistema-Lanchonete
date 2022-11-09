package br.com.elisabete.lanchonete.api.controller;


import br.com.elisabete.lanchonete.modelos.Produto;
import br.com.elisabete.lanchonete.repositorios.ProdutoRepository;
import br.com.elisabete.lanchonete.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProdutoController {


    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;
    @GetMapping
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public Produto findById(@PathVariable Long produtoId){
        return produtoRepository.findById(produtoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save (@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@RequestBody Produto produto){
        produtoService.remover(produto);
    }

}
