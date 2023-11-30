package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class Pessoa {

    @OneToOne
    @JoinColumn(name = "idAcessoPessoa")
    private AcessoPessoa acessoPessoa;

    @OneToOne
    @JoinColumn(name = "idEndereco")
    private Endereco endereco;
    @Column(
            name = "cpf", length = 11
    )
    private String cpf;


    @Column(
            name = "nome", length = 100
    )
    private String nome;
    @Column(
            name = "dataNascimento"
    )
    private LocalDateTime dataNascimento;


    @Column(
            name = "whatsApp"
    )
    private String whatsApp;

    public Pessoa(AcessoPessoa acessoPessoa, Endereco endereco, String cpf, String nome, LocalDateTime dataNascimento, String whatsApp) {
        this.acessoPessoa = acessoPessoa;
        this.endereco = endereco;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.whatsApp = whatsApp;
    }

    public Pessoa() {

    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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


    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }
}
