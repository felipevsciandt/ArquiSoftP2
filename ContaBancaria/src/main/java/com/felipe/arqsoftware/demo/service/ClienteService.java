package com.felipe.arqsoftware.demo.service;

import com.felipe.arqsoftware.demo.model.Cliente;
import com.felipe.arqsoftware.demo.repository.ClienteRepository;
import com.felipe.arqsoftware.demo.service.exceptions.ClientNotFoundException;
import com.felipe.arqsoftware.demo.service.exceptions.CpfCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;


    @Transactional
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Cliente findById(Long id) throws ClientNotFoundException {
        Optional<Cliente> clientObj = repository.findById(id);
        Cliente cliente = clientObj.orElseThrow(() -> new ClientNotFoundException("Operation failed. No account valid for the id on the output."));
        return clientObj.get();
    }

    @Transactional
    public Cliente createCliente(Cliente cliente) {
        String cpfCliente = cliente.getCpf();
        List<Cliente> clientes = repository.findAll();
        for (Cliente clienteCadastrado : clientes) {
            if (cpfCliente.equals(clienteCadastrado.getCpf())) {
                throw new CpfCadastradoException("CPF informado encontra-se em uso");
            }
        }
        return repository.save(cliente);
    }
}
