package com.example.boleto.BoletoApi.controller.exception;

import com.example.boleto.BoletoApi.service.exception.BoletoNotFoundException;
import com.example.boleto.BoletoApi.service.exception.PagamentoRejeitadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(BoletoNotFoundException.class)
    public ResponseEntity<StandardError> boletoNotFoundException(BoletoNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NO_CONTENT;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Erro: Boleto não cadastrado com Id informado");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PagamentoRejeitadoException.class)
    public ResponseEntity<StandardError> pagamentoRejeitadoException(PagamentoRejeitadoException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Erro de pagamento");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
