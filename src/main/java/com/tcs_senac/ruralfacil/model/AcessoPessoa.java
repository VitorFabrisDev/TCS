package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class AcessoPessoa extends Pessoa{

    public AcessoPessoa(long id) {
        super(id);
    }
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    @ManyToOne
    @JoinColumn(
            name = "idPessoa"
    )
    private Pessoa pessoa;

    @Column(
            name = "login"
    )
    private String login;

    @Column(
            name = "senha"
    )
    private String senha;

    @Column(
            name = "qtdAcesso"
    )
    private long qtdAcesso;

    @Column(
            name = "qtUltAcesso"
    )
    private long qtUltAcesso;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getQtdAcesso() {
        return qtdAcesso;
    }

    public void setQtdAcesso(long qtdAcesso) {
        this.qtdAcesso = qtdAcesso;
    }

    public long getQtUltAcesso() {
        return qtUltAcesso;
    }

    public void setQtUltAcesso(long qtUltAcesso) {
        this.qtUltAcesso = qtUltAcesso;
    }
}
