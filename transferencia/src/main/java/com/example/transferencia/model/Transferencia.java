package com.example.transferencia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numContaDestino;
    private int numContaOrigem;
    private Double valor;

    public Transferencia(int numContaDestino, int numContaOrigem, Double valor) {
        this.numContaDestino = numContaDestino;
        this.numContaOrigem = numContaOrigem;
        this.valor = valor;
    }
}
