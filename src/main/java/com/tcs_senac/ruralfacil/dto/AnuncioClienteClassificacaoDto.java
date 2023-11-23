package com.tcs_senac.ruralfacil.dto;


import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.model.AnuncioClienteClassificacao;
import com.tcs_senac.ruralfacil.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AnuncioClienteClassificacaoDto {

    private static ClienteDto cliente;
    private AnuncioDto anuncio;
    private Date dtUltAcesso;
    private long qtdAcesso;
    private double classificacao;
    private long qtdInteracao;

    // getters e setters

    public AnuncioClienteClassificacaoDto() {
    }

    public static AnuncioClienteClassificacaoDto fromEntity(AnuncioClienteClassificacao anuncioClienteClassificacao) {
        AnuncioClienteClassificacaoDto dto = new AnuncioClienteClassificacaoDto();
        dto.setCliente(ClienteDto.fromEntity(anuncioClienteClassificacao.getCliente()));
        dto.setAnuncio(AnuncioDto.fromEntity(anuncioClienteClassificacao.getAnuncio()));
        dto.setDtUltAcesso(anuncioClienteClassificacao.getDtUltAcesso());
        dto.setQtdAcesso(anuncioClienteClassificacao.getQtdAcesso());
        dto.setClassificacao(anuncioClienteClassificacao.getClassificacao());
        dto.setQtdInteracao(anuncioClienteClassificacao.getQtdInteracao());
        return dto;
    }

    public AnuncioClienteClassificacao toEntity() {
        AnuncioClienteClassificacao anuncioClienteClassificacao = new AnuncioClienteClassificacao();
        anuncioClienteClassificacao.setAnuncio(this.getAnuncio());
        anuncioClienteClassificacao.setCliente(this.getCliente());
        anuncioClienteClassificacao.setQtdInteracao(this.getQtdInteracao());
        anuncioClienteClassificacao.setQtdAcesso(this.getQtdAcesso());
        anuncioClienteClassificacao.setClassificacao(this.getClassificacao());
        anuncioClienteClassificacao.setDtUltAcesso(this.getDtUltAcesso());
        return anuncioClienteClassificacao;
    }

    public static Cliente getCliente() {
        return cliente.toEntity();
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public Anuncio getAnuncio() {
        return anuncio.toEntity();
    }

    public void setAnuncio(AnuncioDto anuncio) {
        this.anuncio = anuncio;
    }

    public Date getDtUltAcesso() {
        return dtUltAcesso;
    }

    public void setDtUltAcesso(Date dtUltAcesso) {
        this.dtUltAcesso = dtUltAcesso;
    }

    public long getQtdAcesso() {
        return qtdAcesso;
    }

    public void setQtdAcesso(long qtdAcesso) {
        this.qtdAcesso = qtdAcesso;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public long getQtdInteracao() {
        return qtdInteracao;
    }

    public void setQtdInteracao(long qtdInteracao) {
        this.qtdInteracao = qtdInteracao;
    }

    public static List<AnuncioClienteClassificacaoDto> fromEntity(List<AnuncioClienteClassificacao> anunciosClienteClassificacao) {
        return anunciosClienteClassificacao.stream().map(AnuncioClienteClassificacaoDto::fromEntity).collect(Collectors.toList());
    }

    public static Page<AnuncioClienteClassificacaoDto> fromEntity(Page<AnuncioClienteClassificacao> anunciosClienteClassificacao, Pageable pageable) {
        List<AnuncioClienteClassificacaoDto> anunciosClienteClassificacaoDTO = anunciosClienteClassificacao.stream().map(AnuncioClienteClassificacaoDto::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(anunciosClienteClassificacaoDTO, pageable, anunciosClienteClassificacao.getTotalElements());
    }

}