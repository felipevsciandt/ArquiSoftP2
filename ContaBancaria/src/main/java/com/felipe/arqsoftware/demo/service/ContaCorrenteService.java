package com.felipe.arqsoftware.demo.service;

import com.felipe.arqsoftware.demo.dto.ContaCorrenteDto;
import com.felipe.arqsoftware.demo.model.Cliente;
import com.felipe.arqsoftware.demo.model.ContaCorrente;
import com.felipe.arqsoftware.demo.repository.ClienteRepository;
import com.felipe.arqsoftware.demo.repository.ContaCorrenteRepository;
import com.felipe.arqsoftware.demo.service.exceptions.DeleteFailureException;
import com.felipe.arqsoftware.demo.service.exceptions.NumeroContaInvalidaException;
import com.felipe.arqsoftware.demo.service.exceptions.SaldoInsuficienteException;
import com.felipe.arqsoftware.demo.service.exceptions.ContaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ContaCorrenteService {

    @Autowired
    ContaCorrenteRepository repository;
    @Autowired
    ClienteService clienteService;

    @Autowired
    ExtratoService extratoService;

    @Transactional
    public List<ContaCorrente> findAll() {
        return repository.findAll();
    }
    @Transactional
    public ContaCorrenteDto findById(Long id) throws ContaNotFoundException {
        var conta = repository.findById(id);
        ContaCorrente account = conta.orElseThrow(() -> new ContaNotFoundException("Operation failed. No account valid for the id on the output."));
        return new ContaCorrenteDto(account);
    }

    @Transactional
    public ContaCorrente createAccount(ContaCorrente contaCorrente) {
        List<ContaCorrente> contas = repository.findAll();
        int numeroConta = contaCorrente.getNumeroConta();
        if (verificarSeContaExisteNumeroConta(numeroConta)) {
            throw new NumeroContaInvalidaException("Numero de conta ja cadastrado");
        }
        Cliente cliente = clienteService.findById(contaCorrente.getCliente().getId());
        contaCorrente.setCliente(cliente);
        return repository.save(contaCorrente);
    }

    @Transactional
    public void deleteAccountById(Long id) {
        verificarSeContExiste(id);
        var conta = repository.findById(id).get();
        if (conta.getSaldo() > 0) {
            throw new DeleteFailureException("Para encerrar a conta. Deve-se sacar todo o saldo");
        }
        repository.deleteById(id);
    }


    @Transactional
    public ContaCorrente sacar(Long id ,Double quantidade) {
        verificarSeContExiste(id);
        ContaCorrente conta = repository.findById(id).get();
        validarSaldo(conta, quantidade);
        Double saldoAnterior = conta.getSaldo();
        conta.setSaldo(conta.getSaldo() - quantidade);
        extratoService.gerarExtratoSaque(conta, saldoAnterior);

        return repository.save(conta);
    }

    @Transactional
    public ContaCorrente transferir(int numContaOrigem, Double valor, int numContaDestino) {
        ContaCorrente contaDeposita = verificarNumeroDaConta(numContaOrigem);
        ContaCorrente contaRecebe = verificarNumeroDaConta(numContaDestino);
        validarSaldo(contaDeposita, valor);
        contaDeposita.setSaldo(contaDeposita.getSaldo() - valor);
        contaRecebe.setSaldo(contaRecebe.getSaldo() + valor);
        extratoService.gerarExtratoSaque(contaDeposita, contaDeposita.getSaldo() + valor);
        extratoService.gerarExtratoDeposito(contaRecebe, valor);

        return repository.save(contaDeposita);
    }

    private ContaCorrente verificarNumeroDaConta(int numeroConta) {
        List<ContaCorrente> contas = repository.findAll();
        for (ContaCorrente conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        throw new ContaNotFoundException("Numero de conta invalida");
    }

    @Transactional
    public void pagarBoleto(Long idConta, Double valorBoleto) {
        verificarSeContExiste(idConta);
        ContaCorrente conta = repository.findById(idConta).get();
        Double saldoAnterior = conta.getSaldo() - valorBoleto;
        validarSaldo(conta, valorBoleto);
        extratoService.gerarExtratoBoleto(conta, saldoAnterior, valorBoleto);
        conta.setSaldo(conta.getSaldo() - valorBoleto);
        repository.save(conta);
    }

    public void verificarSeContExiste(Long id) throws ContaNotFoundException {
        Optional<ContaCorrente> contaOptional = repository.findById(id);
        if (!contaOptional.isPresent()) {
            throw new ContaNotFoundException("Conta nao cadastrada para o id informado");
        }
    }

    public boolean verificarSeContaExisteNumeroConta(int numConta) {
        List<ContaCorrente> contas = repository.findAll();
        for (ContaCorrente conta : contas) {
            if (conta.getNumeroConta() == numConta) {
                return true;
            }
        }
        return false;
    }

    private void validarSaldo(ContaCorrente conta, Double quantidade) throws SaldoInsuficienteException {
        if (quantidade > conta.getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar a operacao");
        }
    }

    public ContaCorrente deposito(Long id, Double valor) {
        var opt = repository.findById(id);
        verificarSeContExiste(id);
        ContaCorrente conta = opt.get();
        Double saldoAnterior = conta.getSaldo();
        conta.setSaldo(conta.getSaldo() + valor);
        extratoService.gerarExtratoDepositar(conta, saldoAnterior, valor);
        return conta;
    }
}
