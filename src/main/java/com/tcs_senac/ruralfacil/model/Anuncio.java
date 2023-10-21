package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class Anuncio {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;


    @Column(
            name = "descricao"
    )
    private String descricao;

    @Column(
            name = "valor"
    )
    private Double valor;

    @Column(
            name = "classificacao"
    )
    private String classificacao;

    @Column(
            name = "organico"
    )
    private char organico;

    @Column(
            name = "foto1"
    )
    private String foto1;

    @Column(
            name = "foto2"
    )
    private String foto2;

    @Column(
            name = "foto3"
    )
    private String foto3;

    @Column(
            name = "foto4"
    )
    private String foto4;
    @Column(
            name = "foto5"
    )
    private String foto5;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public char getOrganico() {
        return organico;
    }

    public void setOrganico(char organico) {
        this.organico = organico;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public String getFoto4() {
        return foto4;
    }

    public void setFoto4(String foto4) {
        this.foto4 = foto4;
    }

    public String getFoto5() {
        return foto5;
    }

    public void setFoto5(String foto5) {
        this.foto5 = foto5;
    }
}
