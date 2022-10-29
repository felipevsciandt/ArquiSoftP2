package com.felipe.arqsoftware.demo.service;

import com.felipe.arqsoftware.demo.model.ContaCorrente;
import com.felipe.arqsoftware.demo.model.Extrato;
import com.felipe.arqsoftware.demo.repository.ExtratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtratoService {

    @Autowired
    private ExtratoRepository repository;

    public List<Extrato> findAll() {
        return repository.findAll();
    }

    public Extrato findById(Long id) {
        return repository.findById(id).get();
    }

    public Extrato gerarExtratoSaque(ContaCorrente conta, Double saldoAnterior) {
        Extrato extrato = new Extrato(conta, saldoAnterior, saldoAnterior - conta.getSaldo(), conta.getSaldo());
        return repository.save(extrato);
    }

    public Extrato gerarExtratoDeposito(ContaCorrente conta, Double valor) {
        Extrato extrato = new Extrato(conta, conta.getSaldo() - valor, valor, conta.getSaldo());
        return repository.save(extrato);
    }

    public Extrato gerarExtratoBoleto(ContaCorrente conta, Double saldoAnterior, Double valorDoBoleto) {
        Extrato extrato = new Extrato(conta, saldoAnterior + valorDoBoleto, valorDoBoleto, saldoAnterior);
        return repository.save(extrato);
    }

    public Extrato gerarExtratoDepositar(ContaCorrente conta, Double saldoAnterior, Double valor) {
        Extrato extrato = new Extrato(conta, saldoAnterior, valor, saldoAnterior + valor);
        return repository.save(extrato);
    }
}
