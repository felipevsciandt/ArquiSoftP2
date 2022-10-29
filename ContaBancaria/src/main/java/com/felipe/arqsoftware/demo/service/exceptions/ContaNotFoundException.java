package com.felipe.arqsoftware.demo.service.exceptions;

public class ContaNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ContaNotFoundException(String message) {
        super(message);
    }
}
