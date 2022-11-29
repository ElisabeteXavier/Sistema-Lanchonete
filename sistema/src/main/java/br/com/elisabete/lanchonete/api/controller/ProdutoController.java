package br.com.elisabete.lanchonete.api.controller;


import br.com.elisabete.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.elisabete.lanchonete.modelos.Produto;
import br.com.elisabete.lanchonete.repositorios.ProdutoRepository;
import br.com.elisabete.lanchonete.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{produtoId}")
    public Produto findById(@PathVariable Long produtoId) {
        return produtoRepository.findById(produtoId).orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));
//        return produtoRepository.findById(produtoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto) {
        return produtoService.salvar(produto);
    }

    @DeleteMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@RequestBody Long produtoId) {
        produtoService.remover(produtoId);
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long produtoId, @RequestBody Produto produto) {
        Optional<Produto> produtoAtual = produtoRepository.findById(produtoId);
        if (produtoAtual.isPresent()) {
            BeanUtils.copyProperties(produto, produtoAtual.get(), "id");
            Produto produtoSalvo = produtoService.salvar(produtoAtual.get());
            return ResponseEntity.ok(produtoSalvo);
        }
        return ResponseEntity.notFound().build();

    }

    @PatchMapping("/{produtoId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long produtoId, @RequestBody Map<String, Object> campos) {
        Optional<Produto> produtoAtual = produtoRepository.findById(produtoId);

        if (produtoAtual.isPresent()) {
            merge(campos, produtoAtual.get());
            return atualizar(produtoId, produtoAtual.get());
        }

        return ResponseEntity.notFound().build();
    }

    private void merge(Map<String, Object> dadosOrigem, Produto produtoDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Produto produtoOrigem = objectMapper.convertValue(dadosOrigem, Produto.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade)
                -> {
            Field field = ReflectionUtils.findField(Produto.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, produtoOrigem);
            ReflectionUtils.setField(field, produtoDestino, novoValor);
        });
    }
}
