package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class Anuncio {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    @ManyToOne
    @JoinColumn(
            name = "idCategoriaProdutoAgricultor"
    )
    private CategoriaProdutoAgricultor categoriaProdutoAgricultor;

    @Column(
            name = "descricao"
    )
    private String descricao;

    @Column(
            name = "descricao"
    )
    private Double valor;

    @Column(
            name = "classificacao"
    )
    private String classificacao;

    @Column(
            name = "foto1"
    )
    private String foto1;

    @Column(
            name = "foto2"
    )
    private String foto2;

    @Column(
            name = "foto3"
    )
    private String foto3;

    @Column(
            name = "foto4"
    )
    private String foto4;
    @Column(
            name = "foto5"
    )
    private String foto5;
}
