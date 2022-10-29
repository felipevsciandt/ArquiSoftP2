package com.felipe.arqsoftware.demo.service.exceptions;

public class DeleteFailureException extends RuntimeException {
    public DeleteFailureException(String message) {
        super(message);
    }
}
