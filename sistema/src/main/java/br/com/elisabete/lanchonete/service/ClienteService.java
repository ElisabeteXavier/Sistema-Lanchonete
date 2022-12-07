package br.com.elisabete.lanchonete.service;

import br.com.elisabete.lanchonete.exception.EmailEmUsoException;
import br.com.elisabete.lanchonete.exception.EntidadeDuplicadaExcepition;
import br.com.elisabete.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.elisabete.lanchonete.modelos.Cliente;
import br.com.elisabete.lanchonete.repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    public Cliente salvar(Cliente cliente) {

        if(clienteRepository.findAll().contains(cliente)){
        throw  new EntidadeDuplicadaExcepition("Cliente ja cadastrado!");
        }else if(clienteRepository.findByEmail(cliente.getEmail()) != null){
            throw  new EmailEmUsoException("Email em uso");
        }
            return clienteRepository.save(cliente);

    }
    public void remover(String clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro" +
                            "de cliente com código %s", clienteId));
        }

    }
}
