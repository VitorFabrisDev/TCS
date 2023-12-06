package com.tcs_senac.ruralfacil.model;

import com.querydsl.core.annotations.QueryEntity;
import com.tcs_senac.ruralfacil.model.Enum.Categoria;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@QueryEntity
@Entity
@Table(name = "anuncio", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idAgricultor", "idProduto", "organico"})
})
public class Anuncio {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "idAgricultor")
    private Agricultor agricultor;
    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;

    @Column(name = "categoria")
    private Categoria categoria;
    @Column(
            name = "descricao", length = 1200
    )
    private String descricao;


    @Column(
            name = "valor"
    )
    private Double valor;

    @Column(
            name = "classificacao"
    )
    private Double classificacao;

    @Column(
            name = "organico"
    )
    private boolean organico;

    @Column(
            name = "foto1", columnDefinition = "TEXT"
    )
    private String foto1;

    @Column(
            name = "foto2", columnDefinition = "TEXT"
    )
    private String foto2;

    @Column(
            name = "foto3", columnDefinition = "TEXT"
    )
    private String foto3;

    @Column(
            name = "foto4", columnDefinition = "TEXT"
    )
    private String foto4;
    @Column(
            name = "foto5", columnDefinition = "TEXT"
    )
    private String foto5;
    @Column(
            name = "ativo"
    )
    private boolean ativo;

    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnuncioSazonalidade> anunciosazonalidade;

    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnuncioClienteClassificacao> anuncioClienteClassificacoes;


    public List<AnuncioClienteClassificacao> getAnuncioClienteClassificacoes() {
        return anuncioClienteClassificacoes;
    }

    public void setAnuncioClienteClassificacoes(List<AnuncioClienteClassificacao> anuncioClienteClassificacoes) {
        this.anuncioClienteClassificacoes = anuncioClienteClassificacoes;
    }

    public Anuncio(Long anuncioId) {
    }

    public long getId() {
        return id;
    }

    public List<AnuncioSazonalidade> getAnunciosazonalidade() {
        return anunciosazonalidade;
    }

    public void setAnunciosazonalidade(List<AnuncioSazonalidade> anunciosazonalidade) {
        this.anunciosazonalidade = anunciosazonalidade;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Anuncio(Agricultor agricultor,Produto produto,Categoria categoria, String descricao, Double valor, Double classificacao, boolean organico, String foto1, String foto2, String foto3, String foto4, String foto5,List<AnuncioSazonalidade> anunciosazonalidade, boolean ativo) {
        this.agricultor = agricultor;
        this.produto = produto;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.classificacao = classificacao;
        this.organico = organico;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.foto4 = foto4;
        this.foto5 = foto5;
        this.anunciosazonalidade = anunciosazonalidade;
        this.ativo = ativo;

    }
//teste
    public Anuncio(){

    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Agricultor getAgricultor() {
        return agricultor;
    }

    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
    }

    public boolean isOrganico() {
        return organico;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public Double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Double classificacao) {
        this.classificacao = classificacao;
    }

    public boolean getOrganico() {
        return organico;
    }

    public void setOrganico(boolean organico) {
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

    public List<String> getNomesSazonalidades() {
        return anunciosazonalidade.stream()
                .map(anuncioSazonalidade -> anuncioSazonalidade.getSazonalidade().name())
                .collect(Collectors.toList());
    }
}
