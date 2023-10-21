package com.tcs_senac.ruralfacil.model;

import com.tcs_senac.ruralfacil.Enum.Categoria;

import javax.persistence.*;

@Entity
public class Produto{



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
            name = "idCategoria"
    )
    private Categoria categoria;

    public String getDescricao() {
        return descricao;
    }

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
