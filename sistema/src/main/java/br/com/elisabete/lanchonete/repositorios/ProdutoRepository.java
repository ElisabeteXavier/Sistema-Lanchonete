package br.com.elisabete.lanchonete.repositorios;

import br.com.elisabete.lanchonete.modelos.Cliente;
import br.com.elisabete.lanchonete.modelos.Produto;

import java.util.List;

public interface ProdutoRepository {

    List<Produto> listar();
    Produto buscar(Long id);
    Produto salvar(Produto produto);
    void remover(Cliente cliente);

}
