package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class Sazonalidade {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    @Column(
            name = "mes"
    )
    private char mes;

}
