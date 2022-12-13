package br.com.elisabete.lanchonete;

import br.com.elisabete.lanchonete.exception.EntidadeDuplicadaExcepition;
import br.com.elisabete.lanchonete.exception.EntidadeEmUsoExcepition;
import br.com.elisabete.lanchonete.modelos.Estado;
import br.com.elisabete.lanchonete.service.EstadoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EstadoServiceTests {

    @Autowired
    EstadoService estadoService;
    @Test
    public void deveFalhar_quandoCadastroEstado_ComDadosCorretos(){
// cenário
        Estado novoEstado = new Estado();
        novoEstado.setNome("Alagoas");
//        novoEstado.setSigla("AL");

        novoEstado = estadoService.salvar(novoEstado);

        assertThat(novoEstado).isNotNull();
        assertThat(novoEstado.getId()).isNotNull();
    }
    @Test
    public void deveApontarQuandoUmaEstadoJaFoiCadastrado(){

        Estado novoEstado = new Estado();
        novoEstado.setNome("mg");

        EntidadeDuplicadaExcepition erroEsperado =
                Assertions.assertThrows(
                        EntidadeDuplicadaExcepition.class, () -> { estadoService.salvar(novoEstado);
                        });
        assertThat(erroEsperado).isNotNull();


    }

    @Test
    public void deveFalhar_QuandoExcluirEstadoEmUso() {
        EntidadeEmUsoExcepition erroEsperado =
                Assertions.assertThrows(
                        EntidadeEmUsoExcepition.class, () -> { estadoService.remover(1L);
                        });
        assertThat(erroEsperado).isNotNull();
    }


    @Test
    public void deveFalhar_QuandoDadosdeCadastro_Invalidos() {


        Estado novoEstado = new Estado();
//        novoEstado.setNome(null);


        RuntimeException erroEsperado =
                Assertions.assertThrows(
                        RuntimeException.class, () -> { estadoService.salvar(novoEstado);
                        });
        assertThat(erroEsperado).isNotNull();

    }
}

