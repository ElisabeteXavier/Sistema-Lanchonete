package br.com.elisabete.lanchonete.service;

import br.com.elisabete.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.elisabete.lanchonete.modelos.Produto;
import br.com.elisabete.lanchonete.repositorios.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }
    public void remover(Produto produto) {
        try {
            produtoRepository.deleteById(produto);
        } catch (InvalidDataAccessApiUsageException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro" +
                            "de produto com código %d", produto.getId()));
        }

    }
}
