package com.tcs_senac.ruralfacil.model;

import com.tcs_senac.ruralfacil.model.Enum.Roles;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class AcessoPessoa{

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    @ManyToOne
    @JoinColumn(
            name = "idCliente", nullable = true
    )
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(
            name = "idAgricultor", nullable = true
    )
    private Agricultor agricultor;

    @Column(
            name = "login"
    )
    private String login;

    @Column(
            name = "password"
    )
    private String password;

    @Column(
            name = "qtdAcesso"
    )
    private long qtdAcesso;

    @Column(
            name = "dtUltAcesso"
    )
    private Date dtUltAcesso;

    @Column(
            name = "admin"
    )
    private Roles admin;

    public AcessoPessoa(long id, Cliente cliente, Agricultor agricultor, String login, String password, long qtdAcesso, Date dtUltAcesso, Roles admin) {
        this.id = id;
        this.cliente = cliente;
        this.agricultor = agricultor;
        this.login = login;
        this.password = password;
        this.qtdAcesso = qtdAcesso;
        this.dtUltAcesso = dtUltAcesso;
        this.admin = admin;
    }

    public AcessoPessoa(String login, String password, Roles admin) {
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public AcessoPessoa() {

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Agricultor getAgricultor() {
        return agricultor;
    }

    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getQtdAcesso() {
        return qtdAcesso;
    }

    public void setQtdAcesso(long qtdAcesso) {
        this.qtdAcesso = qtdAcesso;
    }

    public Date getDtUltAcesso() {
        return dtUltAcesso;
    }

    public void setDtUltAcesso(Date dtUltAcesso) {
        this.dtUltAcesso = dtUltAcesso;
    }

    public Roles getAdmin() {
        return admin;
    }

    public void setAdmin(Roles admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcessoPessoa acessoPessoa = (AcessoPessoa) o;
        return Objects.equals(id, acessoPessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
