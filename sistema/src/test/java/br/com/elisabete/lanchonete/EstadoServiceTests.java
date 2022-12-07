package br.com.elisabete.lanchonete;

import br.com.elisabete.lanchonete.modelos.Estado;
import br.com.elisabete.lanchonete.service.EstadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EstadoServiceTests {

    @Autowired
    EstadoService estadoService;
    @Test
    public void deveAtribuirId_quandoCadastroEstado_ComDadosCorretos(){
// cenário
        Estado novoEstado = new Estado();
        novoEstado.setNome("Alagoas");
//        novoEstado.setSigla("AL");
// ação
        novoEstado = estadoService.salvar(novoEstado);
// validação
        assertThat(novoEstado).isNotNull();
        assertThat(novoEstado.getId()).isNotNull();

    }
}