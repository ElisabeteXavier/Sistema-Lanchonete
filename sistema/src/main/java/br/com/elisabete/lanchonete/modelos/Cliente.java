package br.com.elisabete.lanchonete.modelos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity //indica que a classe que vai virar uma tabela
public class Cliente extends Pessoa {
    @EqualsAndHashCode.Include
    @Id //indica que o atributo é chave da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY)// indica que a chave será gerada incrementalmente pelo banco de dados (sequence)
    private Long id;

    public String toString(){
        return this.nome;
    }
}
