package com.tcs_senac.ruralfacil.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class AnuncioClienteClassificacao {

    @ManyToOne
    @JoinColumn(
            name = "idPessoa"
    )
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(
            name = "idAnuncio"
    )
    private Anuncio anuncio;

    @Column(
            name = "dtUltAcesso"
    )
    private Date dtUltAcesso;

    @Column(
            name = "qtdAcesso"
    )
    private long qtdAcesso;

    @Column(
            name = "classificacao"
    )
    private double classificacao;

    @Column(
            name = "qtdInteracao"
    )
 private long qtdInteracao;


}
