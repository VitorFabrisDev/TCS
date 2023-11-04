package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Pessoa {

    @OneToOne
    @JoinColumn(name = "idAcessoPessoa")
    private AcessoPessoa acessoPessoa;
    @Column(
            name = "cpf"
    )
    private String cpf;


    @Column(
            name = "nome"
    )
    private String nome;
    @Column(
            name = "dataNascimento"
    )
    private Date dataNascimento;

    @Column(
            name = "email"
    )
    private String email;

    @Column(
            name = "whatsApp"
    )
    private String whatsApp;

    public Pessoa(AcessoPessoa acessoPessoa, String cpf, String nome, Date dataNascimento, String email, String whatsApp) {
        this.acessoPessoa = acessoPessoa;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.whatsApp = whatsApp;
    }

    public Pessoa() {

    }

    public AcessoPessoa getAcessoPessoa() {
        return acessoPessoa;
    }

    public void setAcessoPessoa(AcessoPessoa acessoPessoa) {
        this.acessoPessoa = acessoPessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }
}
