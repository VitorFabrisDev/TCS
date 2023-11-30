package com.tcs_senac.ruralfacil.model;

import com.tcs_senac.ruralfacil.model.Enum.Roles;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity

public class AcessoPessoa{

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;


    @Column(
            name = "login", length = 50

    )
    private String login;

    @Column(
            name = "password" , length = 100
    )
    private String password;

    @Column(
            name = "qtdAcesso"
    )
    private long qtdAcesso;

    @Column(
            name = "dtUltAcesso"
    )
    private LocalDateTime dtUltAcesso;

    @Column(
            name = "admin"
    )
    private Roles admin;

    public AcessoPessoa(long id, String login, String password, long qtdAcesso, LocalDateTime dtUltAcesso, Roles admin) {
        this.id = id;
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

    public LocalDateTime getDtUltAcesso() {
        return dtUltAcesso;
    }

    public void setDtUltAcesso(LocalDateTime dtUltAcesso) {
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
