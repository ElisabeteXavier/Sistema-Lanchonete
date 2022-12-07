package br.com.elisabete.lanchonete.modelos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    @Column(length = 50)
    private String nome;



}
