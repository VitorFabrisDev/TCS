package com.tcs_senac.ruralfacil.dto;


import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.model.AnuncioSazonalidade;
import com.tcs_senac.ruralfacil.model.Enum.Sazonalidade;



public class AnuncioSazonalidadeDto {


    private long anuncioId;
    private Sazonalidade sazonalidade;

    // construtores, getters e setters

    public static AnuncioSazonalidadeDto fromEntity(AnuncioSazonalidade anuncioSazonalidade) {
        AnuncioSazonalidadeDto dto = new AnuncioSazonalidadeDto();
        dto.setAnuncioId(anuncioSazonalidade.getAnuncio().getId());
        dto.setSazonalidade(anuncioSazonalidade.getSazonalidade());
        return dto;
    }

    public AnuncioSazonalidade toEntity(Anuncio anuncio) {
        AnuncioSazonalidade anuncioSazonalidade = new AnuncioSazonalidade();

        if (anuncio != null) {
            anuncioSazonalidade.setAnuncio(anuncio);
        } else {
            throw new IllegalStateException("Anuncio não pode ser nulo.");
        }

        anuncioSazonalidade.setSazonalidade(this.getSazonalidade());
        return anuncioSazonalidade;
    }


    // getters e setters

    public long getAnuncioId() {
        return anuncioId;
    }

    public void setAnuncioId(long anuncioId) {
        this.anuncioId = anuncioId;
    }

    public Sazonalidade getSazonalidade() {
        return sazonalidade;
    }

    public void setSazonalidade(Sazonalidade sazonalidade) {
        this.sazonalidade = sazonalidade;
    }
}