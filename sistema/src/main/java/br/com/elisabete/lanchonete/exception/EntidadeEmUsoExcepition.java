package br.com.elisabete.lanchonete.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entidade vinculada")
public class EntidadeEmUsoExcepition extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public EntidadeEmUsoExcepition(String mensagem) {
        super(mensagem);
    }
}
