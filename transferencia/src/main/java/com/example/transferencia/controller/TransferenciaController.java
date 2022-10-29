package com.example.transferencia.controller;

import com.example.transferencia.model.Transferencia;
import com.example.transferencia.service.TransferenciaService;
import com.example.transferencia.service.exceptions.TransferenciaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    TransferenciaService service;

    @GetMapping
    public ResponseEntity<List<Transferencia>> findAll() {
        List<Transferencia> boletos = service.findAll();
        return ResponseEntity.ok().body(boletos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transferencia> findById(@PathVariable Long id) throws TransferenciaNotFoundException {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping("/transferir/{contaDestino}/{contaOrigem}/{valor}")
    public ResponseEntity<Transferencia> transferirValor(@PathVariable Long contaDestino, @PathVariable Long contaOrigem,
                                @PathVariable Double valor) {
        Transferencia transferencia = service.transferirValor(contaDestino, contaOrigem, valor);
        return ResponseEntity.accepted().body(transferencia);

    }
}
