package com.example.boleto.BoletoApi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "conta", url = "http://localhost:8081/contas")
public interface ContaFeignClient {

    @PostMapping("/pagarBoleto/{idConta}/{valorBoleto}")
    void pagarBoleto(@PathVariable("idConta") Long idConta, @PathVariable("valorBoleto") Double valorBoleto);
}
