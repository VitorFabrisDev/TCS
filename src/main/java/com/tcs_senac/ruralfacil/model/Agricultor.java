package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Agricultor extends Pessoa{


    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;


    @Column(
            name = "inscricaoEstadual", length = 14
    )
    private String inscricaoEstadual;

    @Column(
            name = "caf", length = 36
    )
    private String caf;

    @Column(
            name = "organico"
    )
    private boolean organico;

    @Column(
            name = "ativo"
    )
    private boolean ativo;


    public Agricultor(AcessoPessoa acessoPessoa, Endereco endereco, String cpf, String nome, LocalDateTime dataNascimento, String whatsApp, long id, String inscricaoEstadual, String caf, boolean organico, boolean ativo) {
        super(acessoPessoa, endereco, cpf, nome, dataNascimento, whatsApp);
        this.id = id;
        this.inscricaoEstadual = inscricaoEstadual;
        this.caf = caf;
        this.organico = organico;
        this.ativo = ativo;
    }


    public Agricultor() {
        super();
    }

    public Agricultor(AcessoPessoa acessoPessoa, Endereco endereco, String cpf, String nome, LocalDateTime dataNascimento, String whatsApp, String inscricaoEstadual, String caf, boolean organico, boolean ativo) {
        super(acessoPessoa, endereco, cpf, nome, dataNascimento, whatsApp);
        this.inscricaoEstadual = inscricaoEstadual;
        this.caf = caf;
        this.ativo = ativo;
        this.organico = organico;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean getOrganico() {
        return organico;
    }

    public void setOrganico(boolean organico) {
        this.organico = organico;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isOrganico() {
        return organico;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
