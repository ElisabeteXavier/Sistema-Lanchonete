package br.com.elisabete.lanchonete;

import br.com.elisabete.lanchonete.exception.EntidadeDuplicadaExcepition;
import br.com.elisabete.lanchonete.exception.EntidadeEmUsoExcepition;
import br.com.elisabete.lanchonete.modelos.Cidade;
import br.com.elisabete.lanchonete.modelos.Cliente;
import br.com.elisabete.lanchonete.modelos.Estado;
import br.com.elisabete.lanchonete.service.CidadeService;
import br.com.elisabete.lanchonete.service.ClienteService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteServiceTests {
    @Autowired
    ClienteService clienteService;

    @Autowired
    CidadeService cidadeService;
    @Test
    public void deveFalhar_quandoCadastroEstado_ComDadosCorretos(){
// cenário
        Cliente novoCliente = new Cliente();
        Cidade cidade = new Cidade();
        Estado estado = new Estado();

        novoCliente.setNome("Nicolas");
        novoCliente.setCpf("92266525387");
        novoCliente.setTelefone("32984147684");
        novoCliente.setEmail("nicolas@gmail.com");
        novoCliente.setDataNascimento(LocalDate.ofEpochDay(2004/02/12));
        estado.setNome("Tangamandapio");
        cidade.setNome("Tangamandapio");
        cidade.setEstado(estado);
        novoCliente.setCidade(cidade);

        novoCliente = clienteService.salvar(novoCliente);

        assertThat(novoCliente).isNotNull();
        assertThat(novoCliente.getCpf()).isNotNull();
    }
    @Test
    public void deveApontarQuandoUmClienteJaFoiCadastrado(){

        Cliente novoCliente = new Cliente();
        Cidade cidade = new Cidade();
        Estado estado = new Estado();

        novoCliente.setNome("Josimar");
        novoCliente.setCpf("11111111111");
        novoCliente.setTelefone("22555554444");
        novoCliente.setEmail("josimar@gmail.com");
        novoCliente.setDataNascimento(LocalDate.ofEpochDay(2003/02/12));
        estado.setNome("Tangamandapio");
        cidade.setNome("Tangamandapio");
        cidade.setEstado(estado);
        novoCliente.setCidade(cidade);
        clienteService.salvar(novoCliente);

        EntidadeDuplicadaExcepition erroEsperado =
                Assertions.assertThrows(
                        EntidadeDuplicadaExcepition.class, () -> { clienteService.salvar(novoCliente);
                        });
        assertThat(erroEsperado).isNotNull();


    }

    @Test
    public void deveFalhar_QuandoExcluirCidadeEmUso() {

        EntidadeEmUsoExcepition erroEsperado =
                Assertions.assertThrows(
                        EntidadeEmUsoExcepition.class, () -> { cidadeService.remover(1L);
                        });
        assertThat(erroEsperado).isNotNull();
    }


    @Test
    public void deveFalhar_QuandoDadosdeCadastro_Invalidos() {


        Cliente novoCliente = new Cliente();
        Cidade cidade = new Cidade();
        Estado estado = new Estado();

        novoCliente.setNome("Ester");
        novoCliente.setCpf("87449413078");
        novoCliente.setTelefone("22555554444");
        novoCliente.setEmail("esterSoares");
        novoCliente.setDataNascimento(LocalDate.ofEpochDay(2003/02/12));
        estado.setNome("Tangamandapio");
        cidade.setNome("Tangamandapio");
        cidade.setEstado(estado);
        novoCliente.setCidade(cidade);



        RuntimeException erroEsperado =
                Assertions.assertThrows(
                        RuntimeException.class, () -> { clienteService.salvar(novoCliente);
                        });
        assertThat(erroEsperado).isNotNull();

    }




        @LocalServerPort
        private int port;

        @Test
        public void deveRetornarStatus201_QuandoConsultarClientes() {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();


            given()
                    .basePath("/clientes")
                    .port(port)
                    .accept(ContentType.JSON)
                    .when()
                    .get()
                    .then()
                    .statusCode(HttpStatus.OK.value());
        }

    }

