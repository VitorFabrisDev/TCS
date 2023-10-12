package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class Agricultor extends Pessoa{

    public Agricultor(long id) {
        super(id);
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    @OneToOne
    @JoinColumn(
            name = "idPessoa"
    )
    private Pessoa pessoa;

    @Column(
            name = "inscricaoEstadual"
    )
    private String inscricaoEstadual;

    @Column(
            name = "caf"
    )
    private String caf;

    @Column(
            name = "organico"
    )
    private char organico;

    @Column(
            name = "ativo"
    )
    private char ativo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCaf() {
        return caf;
    }

    public void setCaf(String caf) {
        this.caf = caf;
    }

    public char getOrganico() {
        return organico;
    }

    public void setOrganico(char organico) {
        this.organico = organico;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }
}
