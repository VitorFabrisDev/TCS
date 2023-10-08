package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class Agricultor extends Pessoa{

    public Agricultor(long id) {
        super(id);
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    @OneToOne
    @JoinColumn(
            name = "idPessoa"
    )
    private Pessoa pessoa;

    @Column(
            name = "inscricaoEstadual"
    )
    private String inscricaoEstadual;

    @Column(
            name = "caf"
    )
    private String caf;

    @Column(
            name = "organico"
    )
    private char organico;

    @Column(
            name = "ativo"
    )
    private char ativo;

}
