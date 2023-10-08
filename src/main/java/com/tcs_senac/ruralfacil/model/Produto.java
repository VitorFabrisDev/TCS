package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class Produto extends Categoria{

    public Produto(long id) {
        super(id);
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;


    @Column(
            name = "descricao"
    )
    private String descricao;

    @Column(
            name = "organico"
    )
    private char organico;

    @ManyToOne
    @JoinColumn(
            name = "idCategoria"
    )
    private Categoria categoria;



}
