package com.felipe.arqsoftware.demo;

import com.felipe.arqsoftware.demo.model.Cliente;
import com.felipe.arqsoftware.demo.model.ContaCorrente;
import com.felipe.arqsoftware.demo.repository.ClienteRepository;
import com.felipe.arqsoftware.demo.repository.ContaCorrenteRepository;
import com.felipe.arqsoftware.demo.service.ClienteService;
import com.felipe.arqsoftware.demo.service.ContaCorrenteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BancarioApplicationTests {

	@Autowired
	ClienteService clienteService;
	@Autowired
	ContaCorrenteService contaCorrenteService;

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;

	@Test
	void finByIdShouldReturnOneClienteWhenCreateClientIsCalled() {
		clienteService.createCliente(new Cliente(1L, "Nome", "CPF", null));
		Assertions.assertEquals(1, clienteRepository.count());
	}

	@Test
	void repositoryCountShouldReturnZeroWhenThereIsNoClientSaved() {
		Assertions.assertEquals(0, clienteRepository.count());
	}

	@Test
	void repositoryCountShouldReturnOneAccountWhenCreateAccountIsCalled() {
		Cliente cliente = new Cliente(1L, "Nome", "CPF", null);
		clienteService.createCliente(cliente);
		ContaCorrente conta = new ContaCorrente(1L, 1, 123, cliente, 0.0);
		contaCorrenteService.createAccount(conta);
		Assertions.assertEquals(1, contaCorrenteRepository.count());
	}


}
