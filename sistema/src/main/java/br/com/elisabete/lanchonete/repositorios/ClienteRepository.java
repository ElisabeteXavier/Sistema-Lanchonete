package br.com.elisabete.lanchonete.repositorios;

import br.com.elisabete.lanchonete.modelos.Cliente;

import java.util.List;

public interface ClienteRepository {
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long cliente);
}
