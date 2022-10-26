package br.com.elisabete.lanchonete.repositorios;

import br.com.elisabete.lanchonete.modelos.Estado;
import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado cliente);
    void remover(Estado cliente);
}
