package com.tcs_senac.ruralfacil.model;


import javax.persistence.*;


@Entity
public class Cliente{



    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

