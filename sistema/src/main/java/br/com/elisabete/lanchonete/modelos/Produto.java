package br.com.elisabete.lanchonete.modelos;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @EqualsAndHashCode.Include
    @NotNull
    @Column(length = 50, nullable = false)
    private String nome;
    @NotNull
    @Column(length = 50, nullable = false)
    private int qtdEstoque;

    @NotNull
    @Column(length = 50, nullable = false)
    private double valorUnitario;

    @NotNull
    private Boolean ativo;
}
