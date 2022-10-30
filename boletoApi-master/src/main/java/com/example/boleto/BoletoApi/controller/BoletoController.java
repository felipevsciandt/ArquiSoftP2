package com.example.boleto.BoletoApi.controller;

import com.example.boleto.BoletoApi.model.Boleto;
import com.example.boleto.BoletoApi.service.BoletoService;
import com.example.boleto.BoletoApi.service.exception.BoletoNotFoundException;
import com.example.boleto.BoletoApi.service.exception.PagamentoRejeitadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/boletos")
public class BoletoController {

    @Autowired
    BoletoService service;

    @GetMapping
    public ResponseEntity<List<Boleto>> findAll() {
        List<Boleto> boletos = service.findAll();
        return ResponseEntity.ok().body(boletos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleto> findById(@PathVariable Long id) throws BoletoNotFoundException {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Boleto> createBoleto(@RequestBody Boleto boleto) {
        Boleto obj = service.createBoleto(boleto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(boleto.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping("/{id}")
    public void deleteBoletoById(@PathVariable Long id) {
        service.deleteBoletoById(id);
    }

    @PostMapping("/pagarBoleto/{idConta}/{idBoleto}")
    public ResponseEntity<Boleto> pagarBoleto(@PathVariable Long idConta, @PathVariable Long idBoleto)
            throws BoletoNotFoundException, PagamentoRejeitadoException {
        return ResponseEntity.accepted().body(service.pagar(idConta, idBoleto));
    }
}
