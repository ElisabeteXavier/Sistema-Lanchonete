package br.com.elisabete.lanchonete.service;

import br.com.elisabete.lanchonete.exception.EntidadeDuplicadaExcepition;
import br.com.elisabete.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.elisabete.lanchonete.modelos.Cidade;
import br.com.elisabete.lanchonete.repositorios.CidadeRepository;
import br.com.elisabete.lanchonete.repositorios.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    public Cidade salvar(Cidade cidade) {
        if(cidadeRepository.findAll().contains(cidade)){
            throw  new EntidadeDuplicadaExcepition("Cidade ja cadastrada!");
        }else if(!estadoRepository.existsById(cidade.getEstado().getId())){
            throw new EntidadeNaoEncontradaException("Estado não encontrado!");
        }else
            return cidadeRepository.save(cidade);
    }
    public void remover(Long cidadeId) {
        try {

            cidadeRepository.deleteById(cidadeId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro" +
                            "de cidade com código %d", cidadeId));
        }



    }
}
