package br.com.elisabete.lanchonete.modelos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity //indica que a classe que vai virar uma tabela
public class Cliente extends Pessoa {

    @OneToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
    public String toString(){
        return this.nome;
    }
}
