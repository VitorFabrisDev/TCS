package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class Endereco{
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    @Column(
            name = "logradouro", length = 255
    )
    private String logradouro;

    @Column(
            name = "numero", length = 10
    )
    private String numero;

    @Column(
            name = "complemento", length = 255
    )
    private String complemento;

    @Column(
            name = "bairro", length = 60
    )
    private String bairro;

    @Column(
            name = "municipio", length = 60
    )
    private String municipio;

    @Column(
            name = "cep", length = 9
    )
    private String cep;

    @Column(
            name = "inscricaoIncra", length = 20
    )
    private String inscricaoIncra;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
