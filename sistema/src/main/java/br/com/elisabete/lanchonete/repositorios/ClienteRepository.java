package br.com.elisabete.lanchonete.repositorios;

import br.com.elisabete.lanchonete.modelos.Cidade;
import br.com.elisabete.lanchonete.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository< Cliente, String> {

    Cliente findByEmail(String email);

    Cliente findByCidade(Cidade cidade);

}
