package br.com.elisabete.lanchonete.excepitionhadler;

import br.com.elisabete.lanchonete.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntidadeDuplicadaExcepition.class)
    public ResponseEntity<?> tratarEntidadeDuplicadaException(EntidadeDuplicadaExcepition ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(EntidadeEmUsoExcepition.class)
    public ResponseEntity<?> tratarEntidadeVinculadaException(EntidadeEmUsoExcepition ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EmailEmUsoException.class)
    public ResponseEntity<?> tratarEmailEmUsoException(EmailEmUsoException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<?> tratarCpfInvalidoException(CpfInvalidoException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problema.builder()
                    .dataHora(OffsetDateTime.now())
                    .mensagem(status.getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = Problema.builder()
                    .dataHora(OffsetDateTime.now())
                    .mensagem((String) body)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }


}
