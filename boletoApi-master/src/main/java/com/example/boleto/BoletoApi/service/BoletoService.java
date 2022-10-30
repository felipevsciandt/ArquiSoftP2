package com.example.boleto.BoletoApi.service;

import com.example.boleto.BoletoApi.client.ContaFeignClient;
import com.example.boleto.BoletoApi.model.Boleto;
import com.example.boleto.BoletoApi.model.EnumStatus;
import com.example.boleto.BoletoApi.repository.BoletoRepository;
import com.example.boleto.BoletoApi.service.exception.BoletoNotFoundException;
import com.example.boleto.BoletoApi.service.exception.PagamentoRejeitadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoletoService {

    @Autowired
    BoletoRepository repository;

    @Autowired
    ContaFeignClient client;

    @Transactional
    public List<Boleto> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Boleto findById(Long id) {

        Optional<Boleto> boletoObj = repository.findById(id);
        Boleto boleto = boletoObj.orElseThrow(() -> new BoletoNotFoundException("Não há boleto cadastrado com esse Id"));
        return boleto;

    }

    @Transactional
    public Boleto createBoleto(Boleto boleto) {
        return repository.save(boleto);
    }

    @Transactional
    public void deleteBoletoById(Long id) {
        repository.deleteById(id);
    }

    public Boleto pagar(Long idConta, Long idBoleto) {

            Optional<Boleto> object = repository.findById(idBoleto);
            if (!object.isPresent()) {
                throw new BoletoNotFoundException("Boleto nao cadastrado com esse Id");
            }
            Boleto boleto = object.get();
            verificarSeBoletoEstaPago(boleto);
            client.pagarBoleto(idConta, boleto.getValor());
            boleto.setStatus(EnumStatus.PAGO);
            return repository.save(boleto);

    }

    public void verificarSeBoletoEstaPago(Boleto boleto) {
        if (boleto.getStatus() == EnumStatus.PAGO) {
            throw new PagamentoRejeitadoException("O boleto informado está pago!");
        }
    }
}
