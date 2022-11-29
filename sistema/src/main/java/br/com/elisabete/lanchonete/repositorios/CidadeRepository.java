package br.com.elisabete.lanchonete.repositorios;

import br.com.elisabete.lanchonete.modelos.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {


}
