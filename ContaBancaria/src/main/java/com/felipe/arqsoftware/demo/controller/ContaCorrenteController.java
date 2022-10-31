package com.felipe.arqsoftware.demo.controller;

import com.felipe.arqsoftware.demo.dto.ContaCorrenteDto;
import com.felipe.arqsoftware.demo.model.ContaCorrente;
import com.felipe.arqsoftware.demo.service.ContaCorrenteService;
import com.felipe.arqsoftware.demo.service.exceptions.ContaNotFoundException;
import com.felipe.arqsoftware.demo.service.exceptions.DeleteFailureException;
import com.felipe.arqsoftware.demo.service.exceptions.SaldoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.security.auth.login.AccountNotFoundException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaCorrenteController {

    @Autowired
    ContaCorrenteService service;

    @GetMapping
    public ResponseEntity<List<ContaCorrente>> findAll() {
        List<ContaCorrente> contas = service.findAll();
        return ResponseEntity.ok().body(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaCorrenteDto> findById(@PathVariable Long id) throws ContaNotFoundException {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContaCorrente> createAccount(@RequestBody ContaCorrente contaCorrente) {
        ContaCorrente conta = service.createAccount(contaCorrente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(contaCorrente.getId()).toUri();
        return ResponseEntity.created(uri).body(conta);
    }

//    @PutMapping
//    public ResponseEntity<ContaCorrente> updateAccount(@RequestBody ContaCorrenteDto contaCorrenteDto) {
//        return ResponseEntity.accepted().body(service.updateAccount(contaCorrenteDto));
//    }

    @PutMapping("/{id}/saque/{valor}")
    public ResponseEntity<ContaCorrente> operacaoSaque(@PathVariable Long id, @PathVariable Double valor) throws SaldoInsuficienteException {
        return ResponseEntity.accepted().body(service.sacar(id, valor));
    }

    @PostMapping("/transferir/{numContaOrigem}/{valor}/{numContaDestino}")
    public ResponseEntity<ContaCorrente> operacaoTransferencia(@PathVariable int numContaOrigem,
                                                          @PathVariable Double valor, @PathVariable int numContaDestino) throws SaldoInsuficienteException {
        return ResponseEntity.accepted().body(service.transferir(numContaOrigem, valor, numContaDestino));
    }

    @PutMapping("{id}/depositar/{valor}")
    public ResponseEntity<ContaCorrente> operacaoDepositar(@PathVariable Long id, @PathVariable Double valor) {
        return ResponseEntity.accepted().body(service.deposito(id, valor));
    }

    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable Long id) throws DeleteFailureException {
        service.deleteAccountById(id);
    }

    @PostMapping("/pagarBoleto/{idConta}/{valorBoleto}")
    public void pagarBoleto(@PathVariable("idConta") Long idConta, @PathVariable("valorBoleto") Double valorBoleto)
            throws SaldoInsuficienteException, ContaNotFoundException{
        service.pagarBoleto(idConta, valorBoleto);
    }
}
