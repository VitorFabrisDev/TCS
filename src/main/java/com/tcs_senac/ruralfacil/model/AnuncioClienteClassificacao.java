package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class AnuncioClienteClassificacao extends EntityId{


    @ManyToOne
    @JoinColumn(
            name = "idCliente"
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnuncioClienteClassificacao that = (AnuncioClienteClassificacao) o;
        return Objects.equals(cliente, that.cliente) &&
                Objects.equals(anuncio, that.anuncio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, anuncio);
    }

}
