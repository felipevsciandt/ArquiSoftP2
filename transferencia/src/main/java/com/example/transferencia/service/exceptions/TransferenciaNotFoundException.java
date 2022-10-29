package com.example.transferencia.service.exceptions;

public class TransferenciaNotFoundException extends RuntimeException {
    public TransferenciaNotFoundException() {
    }

    public TransferenciaNotFoundException(String message) {
        super(message);
    }
}
