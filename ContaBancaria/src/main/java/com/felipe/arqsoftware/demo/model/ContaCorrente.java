package com.felipe.arqsoftware.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ContaCorrente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int agencia;
    private int numeroConta;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
    private Double saldo;
    @OneToMany(mappedBy = "contaCorrente")
    private List<Extrato> extratos;

    public ContaCorrente (Long id, int agencia, int numeroConta){
        this.id = id;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
    }

    public ContaCorrente(Long id, int agencia, int numeroConta, Cliente cliente, Double saldo) {
        this.id = id;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = saldo;
    }

}
