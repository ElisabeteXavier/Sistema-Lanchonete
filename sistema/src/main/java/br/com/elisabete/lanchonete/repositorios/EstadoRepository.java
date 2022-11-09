package br.com.elisabete.lanchonete.repositorios;

import br.com.elisabete.lanchonete.modelos.Estado;
import java.util.List;

public interface EstadoRepository {

    List<Estado> findAll();
    Estado findById(Long id);
    Estado save(Estado estado);
    void deleteById(Estado estado);
}
