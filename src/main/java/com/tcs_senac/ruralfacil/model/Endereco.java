package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class Endereco extends Pessoa{
    public Endereco(long id) {
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
            name = "tipoEndereco"
    )
    private char tipoEndereco;

    @Column(
            name = "logradouro"
    )
    private String logradouro;

    @Column(
            name = "numero"
    )
    private String numero;

    @Column(
            name = "complemento"
    )
    private String complemento;

    @Column(
            name = "bairro"
    )
    private String bairro;

    @Column(
            name = "municipio"
    )
    private String municipio;

    @Column(
            name = "cep"
    )
    private String cep;

    @Column(
            name = "inscricaoIncra"
    )
    private String inscricaoIncra;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public char getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(char tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getInscricaoIncra() {
        return inscricaoIncra;
    }

    public void setInscricaoIncra(String inscricaoIncra) {
        this.inscricaoIncra = inscricaoIncra;
    }
}
