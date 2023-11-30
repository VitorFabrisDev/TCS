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
            name = "nomeProduto", length = 255
    )
    private String nomeProduto;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }


}
