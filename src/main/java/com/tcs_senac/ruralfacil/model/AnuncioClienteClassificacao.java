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

    public AnuncioClienteClassificacao(Cliente cliente, Anuncio anuncio, Date dtUltAcesso, long qtdAcesso, double classificacao, long qtdInteracao) {
        this.cliente = cliente;
        this.anuncio = anuncio;
        this.dtUltAcesso = dtUltAcesso;
        this.qtdAcesso = qtdAcesso;
        this.classificacao = classificacao;
        this.qtdInteracao = qtdInteracao;
    }

    public AnuncioClienteClassificacao() {

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Date getDtUltAcesso() {
        return dtUltAcesso;
    }

    public void setDtUltAcesso(Date dtUltAcesso) {
        this.dtUltAcesso = dtUltAcesso;
    }

    public long getQtdAcesso() {
        return qtdAcesso;
    }

    public void setQtdAcesso(long qtdAcesso) {
        this.qtdAcesso = qtdAcesso;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public long getQtdInteracao() {
        return qtdInteracao;
    }

    public void setQtdInteracao(long qtdInteracao) {
        this.qtdInteracao = qtdInteracao;
    }

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
