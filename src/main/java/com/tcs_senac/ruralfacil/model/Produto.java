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


    @ManyToOne
    @JoinColumn(
            name = "idCategoria"
    )
    private Categoria categoria;

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
