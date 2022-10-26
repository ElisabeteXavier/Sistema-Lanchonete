package br.com.elisabete.lanchonete.modelos;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Produto {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nome;
    private int qtdEstoque;
    private double valorUnitario;
}
