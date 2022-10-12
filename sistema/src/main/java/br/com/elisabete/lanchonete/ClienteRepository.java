package br.com.elisabete.lanchonete;

import br.com.elisabete.lanchonete.Cliente;

import java.util.List;

public interface ClienteRepository {
    List<Cliente> listar();
    Cliente buscar(Long id);
    Cliente salvar(Cliente cliente);
    void remover(Cliente cliente);
}
