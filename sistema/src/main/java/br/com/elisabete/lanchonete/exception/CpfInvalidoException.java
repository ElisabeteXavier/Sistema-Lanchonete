package br.com.elisabete.lanchonete.exception;

public class CpfInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public CpfInvalidoException(String mensagem){
        super(mensagem);
    }
}
