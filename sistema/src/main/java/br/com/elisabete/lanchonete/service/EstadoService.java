package br.com.elisabete.lanchonete.service;

import br.com.elisabete.lanchonete.exception.EntidadeDuplicadaExcepition;
import br.com.elisabete.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.elisabete.lanchonete.exception.EntidadeVinculadaExcepition;
import br.com.elisabete.lanchonete.modelos.Estado;
import br.com.elisabete.lanchonete.repositorios.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;
    public Estado salvar(Estado estado) {

       if( estadoRepository.findAll().contains(estado)){
           throw  new EntidadeDuplicadaExcepition("Estado ja cadastrado!");
       }else
        return estadoRepository.save(estado);
    }
    public void remover(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro" +
                            "de estado com código %d", estadoId));
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeVinculadaExcepition("A cidade está vinculada a um estado");
        }

    }
}
