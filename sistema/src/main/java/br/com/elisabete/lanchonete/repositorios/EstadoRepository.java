package br.com.elisabete.lanchonete.repositorios;

import br.com.elisabete.lanchonete.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository< Estado, Long> {


}
