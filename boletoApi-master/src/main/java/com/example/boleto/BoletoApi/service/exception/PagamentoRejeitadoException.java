package com.example.boleto.BoletoApi.service.exception;

public class PagamentoRejeitadoException extends RuntimeException {
    public PagamentoRejeitadoException(String message) {
        super(message);
    }
}
