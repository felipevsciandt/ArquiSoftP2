package com.felipe.arqsoftware.demo.service.exceptions;

public class NumeroContaInvalidaException extends RuntimeException {
    public NumeroContaInvalidaException() {
    }

    public NumeroContaInvalidaException(String message) {
        super(message);
    }
}
