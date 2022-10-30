package com.example.transferencia.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "conta", url = "http://localhost:8080/contas/transferir")
public interface ContaClient {

    @PostMapping("/{contaOrigem}/{valor}/{contaDestino}")
    void operacaoTransferencia(@PathVariable("contaOrigem") int contaOrigem, @PathVariable("valor") Double valor,
                               @PathVariable("contaDestino") int contaDestino);
}