package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    @Column(
            name = "descricao"
    )
    private String descricao;

    public Categoria(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
