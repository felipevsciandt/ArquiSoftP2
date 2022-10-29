package com.felipe.arqsoftware.demo.dto;

import com.felipe.arqsoftware.demo.model.Cliente;
import com.felipe.arqsoftware.demo.model.ContaCorrente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ContaCorrenteDto {
    private Long id;
    private int agencia;
    private int numeroConta;

    private Cliente titular;
    private double saldo;

    public ContaCorrenteDto(ContaCorrente conta) {
        this.id = conta.getId();
        this.agencia = conta.getAgencia();
        this.numeroConta = conta.getNumeroConta();
    }


    public ContaCorrente convertToContaCorrente(){
        return new ContaCorrente(this.getId(), this.getAgencia(), this.getNumeroConta(),
                this.titular = getTitular(), this.saldo = getSaldo());
    }
}
