package br.com.elisabete.lanchonete.repositorios;

import br.com.elisabete.lanchonete.modelos.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> findAll();
    Cidade findById(Long id);
    Cidade save(Cidade cidade);
    void deleteById(Cidade cidade);
}
