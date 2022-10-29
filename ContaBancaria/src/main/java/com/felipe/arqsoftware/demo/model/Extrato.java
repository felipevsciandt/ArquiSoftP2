
package com.felipe.arqsoftware.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Extrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private ContaCorrente contaCorrente;
    private Double movimentacao;
    private Double saldoAnterior;
    private Double novoSaldo;

    public Extrato(ContaCorrente contaCorrente, Double saldoAnterior, Double movimentacao, Double novoSaldo) {
        this.contaCorrente = contaCorrente;
        this.saldoAnterior = saldoAnterior;
        this.movimentacao = movimentacao;
        this.novoSaldo = novoSaldo;
    }

}


