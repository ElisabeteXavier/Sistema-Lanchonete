package br.com.elisabete.lanchonete.api.controller;


import br.com.elisabete.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.elisabete.lanchonete.modelos.Cidade;
import br.com.elisabete.lanchonete.repositorios.CidadeRepository;
import br.com.elisabete.lanchonete.service.CidadeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.*;


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

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);
        if (cidadeAtual.isPresent()) {
            BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
            Cidade cidadeSalvo = cidadeService.salvar(cidadeAtual.get());
            return ResponseEntity.ok(cidadeSalvo);
        }
        return ResponseEntity.notFound().build();

    }

    @PatchMapping("/{cidadeId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long cidadeId, @RequestBody Map<String, Object> campos) {
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);

        if (cidadeAtual.isPresent()) {
            merge(campos, cidadeAtual.get());
            return atualizar(cidadeId, cidadeAtual.get());
        }

        return ResponseEntity.notFound().build();
    }

    private void merge(Map<String, Object> dadosOrigem, Cidade cidadeDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Cidade cidadeOrigem = objectMapper.convertValue(dadosOrigem, Cidade.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade)
                -> {
            Field field = ReflectionUtils.findField(Cidade.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, cidadeOrigem);
            ReflectionUtils.setField(field, cidadeDestino, novoValor);
        });
    }


}
