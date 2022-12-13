package br.com.elisabete.lanchonete.modelos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity //indica que a classe que vai virar uma tabela
public class Cliente extends Pessoa {

    @NotNull
    @ManyToOne(cascade= CascadeType.ALL )
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
    public String toString(){
        return this.nome;
    }
}
