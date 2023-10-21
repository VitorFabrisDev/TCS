package com.tcs_senac.ruralfacil.model;

import com.tcs_senac.ruralfacil.Enum.Sazonalidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AnuncioSazonalidade {

    @ManyToOne
    @JoinColumn(
            name = "idAnuncioo"
    )
    private Anuncio anuncio;
    @Column(
            name = "Sazonalidade"
    )
    private Sazonalidade sazonalidade;

}
