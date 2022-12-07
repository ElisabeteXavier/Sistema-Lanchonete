package br.com.elisabete.lanchonete.exception;


import java.io.Serial;

public class EmailEmUsoException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public EmailEmUsoException(String mensagem){
        super(mensagem);
    }
}
