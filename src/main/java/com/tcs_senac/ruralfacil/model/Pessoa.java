package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

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

    public Pessoa(long id) {
        this.id = id;
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

    public void setNome(String cpf) {
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
