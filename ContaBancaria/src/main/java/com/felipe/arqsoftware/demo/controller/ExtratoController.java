package com.felipe.arqsoftware.demo.controller;

import com.felipe.arqsoftware.demo.model.Extrato;
import com.felipe.arqsoftware.demo.service.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/extratos")
public class ExtratoController {
    @Autowired
    ExtratoService service;

    @GetMapping
    public ResponseEntity<List<Extrato>> findAll() {
        List<Extrato> extratos = service.findAll();
        return ResponseEntity.ok().body(extratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Extrato> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
