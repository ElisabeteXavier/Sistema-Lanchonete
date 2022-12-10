package br.com.elisabete.lanchonete.modelos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class Pessoa {
    @NotNull
    @Column(length = 50, nullable = false)
    protected String nome;
    @EqualsAndHashCode.Include
    @Id
    @Column(nullable = false, length = 11)
    protected String cpf;
    @Column(length = 13)
    protected String telefone;

    @NotNull
    @Email
    @Column( nullable = false, length = 40)
    protected String email;
    protected LocalDate dataNascimento;
}

