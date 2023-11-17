package com.tcs_senac.ruralfacil.dto;


import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.model.AnuncioSazonalidade;
import com.tcs_senac.ruralfacil.model.Enum.Sazonalidade;

import java.util.List;
import java.util.stream.Collectors;

public class AnuncioSazonalidadeDto {

    private Long anuncioId;
    private Sazonalidade sazonalidade;

    public AnuncioSazonalidadeDto(Sazonalidade sazonalidade) {
        this.sazonalidade = sazonalidade;
    }

    public AnuncioSazonalidadeDto() {

    }




    public Long getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(Long anuncioId) {
        this.anuncioId = anuncioId;
    }

    public Sazonalidade getSazonalidade() {
        return sazonalidade;
    }

    public void setSazonalidade(Sazonalidade sazonalidade) {
        this.sazonalidade = sazonalidade;
    }

    public static AnuncioSazonalidadeDto fromEntity(AnuncioSazonalidade anuncioSazonalidade) {
        AnuncioSazonalidadeDto dto = new AnuncioSazonalidadeDto();
        dto.setSazonalidade(anuncioSazonalidade.getSazonalidade());
        return dto;
    }

    public AnuncioSazonalidade toEntity(Anuncio anuncio) {
        AnuncioSazonalidade anuncioSazonalidade = new AnuncioSazonalidade();
        anuncioSazonalidade.setAnuncio(anuncio);
        anuncioSazonalidade.setSazonalidade(this.getSazonalidade());
        return anuncioSazonalidade;
    }
    public static List<AnuncioSazonalidadeDto> fromEntityList(List<AnuncioSazonalidade> sazonalidades) {
        return sazonalidades.stream().map(AnuncioSazonalidadeDto::fromEntity).collect(Collectors.toList());
    }
}