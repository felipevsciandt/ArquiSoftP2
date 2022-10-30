package com.example.transferencia.service;

import com.example.transferencia.client.ContaClient;
import com.example.transferencia.model.Transferencia;
import com.example.transferencia.repository.TransferenciaRepository;
import com.example.transferencia.service.exceptions.TransferenciaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService {

    @Autowired
    ContaClient client;
    @Autowired
    TransferenciaRepository repository;

    @Transactional
    public List<Transferencia> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Transferencia findById(Long id) {
        Transferencia transferencia = null;
        Optional<Transferencia> optional = repository.findById(id);
        if (optional.isPresent()) {
            transferencia = optional.get();
        } else {
            throw new TransferenciaNotFoundException("Id não cadastrado entre as trasnferencias");
        }
        return transferencia;
    }

    @Transactional
    public Transferencia transferirValor(int contaOrigem, Double valor, int contaDestino) {
        client.operacaoTransferencia(contaOrigem, valor, contaDestino);
        Transferencia transferencia = new Transferencia(contaDestino, contaOrigem, valor);
        return repository.save(transferencia);
    }
}
