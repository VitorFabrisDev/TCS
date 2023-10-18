package com.tcs_senac.ruralfacil.model;


import javax.persistence.*;


@Entity
public class Cliente extends  Pessoa{

    public Cliente(long id) {
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


}

