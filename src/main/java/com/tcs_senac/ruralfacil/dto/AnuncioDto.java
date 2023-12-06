package com.tcs_senac.ruralfacil.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.model.Enum.Categoria;
import com.tcs_senac.ruralfacil.model.Enum.Sazonalidade;
import com.tcs_senac.ruralfacil.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class AnuncioDto {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private Agricultor agricultor;
    private Produto produto;
    private Categoria categoria;
    private String descricao;
    private Double valor;
    private Double classificacao;
    private boolean organico;
    private String foto1;
    private String foto2;
    private String foto3;
    private String foto4;
    private String foto5;
    private boolean ativo;

    private List<String> sazonalidades;
//teste
    public Produto getProduto() {
        return produto;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

    public List<String> getSazonalidades() {
        return sazonalidades;
    }

    public void setSazonalidades(List<String> sazonalidades) {
        this.sazonalidades = sazonalidades;
    }

    public AnuncioDto() {
    }


    public static AnuncioDto fromEntity(Anuncio anuncio) {
        AnuncioDto dto = new AnuncioDto();
        dto.setId(anuncio.getId());
        dto.setAgricultor(anuncio.getAgricultor());
        dto.setProduto(anuncio.getProduto());
        dto.setCategoria(anuncio.getCategoria());
        dto.setDescricao(anuncio.getDescricao());
        dto.setValor(anuncio.getValor());
        dto.setClassificacao(anuncio.getClassificacao());
        dto.setOrganico(anuncio.getOrganico());
        dto.setSazonalidades(dto.getSazonalidades());
        dto.setFoto1(anuncio.getFoto1());
        dto.setFoto2(anuncio.getFoto2());
        dto.setFoto3(anuncio.getFoto3());
        dto.setFoto4(anuncio.getFoto4());
        dto.setFoto5(anuncio.getFoto5());
        dto.setAtivo(anuncio.isAtivo());
        return dto;
    }


    public Anuncio toEntity() {
        Anuncio anuncio = new Anuncio();
        anuncio.setAgricultor(this.getAgricultor());
        anuncio.setProduto(this.getProduto());
        anuncio.setCategoria(this.getCategoria());
        anuncio.setDescricao(this.getDescricao());
        anuncio.setValor(this.getValor());
        anuncio.setClassificacao(this.getClassificacao());
        anuncio.setOrganico(this.getOrganico());
        anuncio.setFoto1(this.getFoto1());
        anuncio.setFoto2(this.getFoto2());
        anuncio.setFoto3(this.getFoto3());
        anuncio.setFoto4(this.getFoto4());
        anuncio.setFoto5(this.getFoto5());
        anuncio.setAtivo(this.isAtivo());
        return anuncio;
    }


    public static List<AnuncioDto> fromEntity(List<Anuncio> anuncios) {
        return anuncios.stream().map(AnuncioDto::fromEntity).collect(Collectors.toList());
    }

    public static Page<AnuncioDto> fromEntity(Page<Anuncio> anuncios, Pageable pageable) {
        List<AnuncioDto> anunciosDTO = anuncios.stream().map(AnuncioDto::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(anunciosDTO, pageable, anuncios.getTotalElements());
    }

    public List<AnuncioSazonalidadeDto> getAnunciosazonalidade() {
        if (sazonalidades != null) {
            return sazonalidades.stream()
                    .map(s -> new AnuncioSazonalidadeDto(Sazonalidade.valueOf(s)))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private List<String> convertAnuncioSazonalidadesToStrings() {
        if (sazonalidades != null) {
            return sazonalidades.stream()
                    .map(anuncioSazonalidade -> sazonalidades.toString()) // ou anuncioSazonalidade.toString(), dependendo da implementação de AnuncioSazonalidade
                    .collect(Collectors.toList());
        }
        return null;
    }

}