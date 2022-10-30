package com.felipe.arqsoftware.demo.service.exceptions;

public class CpfCadastradoException extends RuntimeException {
    public CpfCadastradoException() {
    }

    public CpfCadastradoException(String message) {
        super(message);
    }
}
