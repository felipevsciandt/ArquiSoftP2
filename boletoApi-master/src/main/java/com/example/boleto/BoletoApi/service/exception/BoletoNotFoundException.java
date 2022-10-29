package com.example.boleto.BoletoApi.service.exception;

public class BoletoNotFoundException extends RuntimeException {
    public BoletoNotFoundException(String message) {
        super(message);
    }
}
