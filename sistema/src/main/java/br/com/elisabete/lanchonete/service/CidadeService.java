package br.com.elisabete.lanchonete.service;

import br.com.elisabete.lanchonete.exception.EntidadeNaoEncontradaException;
import br.com.elisabete.lanchonete.exception.EntidadeVinculadaExcepition;
import br.com.elisabete.lanchonete.modelos.Cidade;
import br.com.elisabete.lanchonete.repositorios.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    public Cidade salvar(Cidade cidade) {
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
