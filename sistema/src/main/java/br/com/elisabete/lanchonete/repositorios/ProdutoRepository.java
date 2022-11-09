package br.com.elisabete.lanchonete.repositorios;

import br.com.elisabete.lanchonete.modelos.Cliente;
import br.com.elisabete.lanchonete.modelos.Produto;

import java.util.List;

public interface ProdutoRepository {

    List<Produto> findAll();
    Produto findById(Long id);
    Produto save(Produto produto);
    void deleteById(Produto produto);

}
