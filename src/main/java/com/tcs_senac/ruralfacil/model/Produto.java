package com.tcs_senac.ruralfacil.model;

import com.tcs_senac.ruralfacil.model.Enum.Categoria;

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



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
