package com.tcs_senac.ruralfacil.model;

import com.tcs_senac.ruralfacil.model.Enum.Sazonalidade;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AnuncioSazonalidade extends EntityId {


    @ManyToOne
    @JoinColumn(
            name = "idAnuncio"
    )
    private Anuncio anuncio;
    @Column(
            name = "sazonalidade"
    )
    private Sazonalidade sazonalidade;

    public AnuncioSazonalidade() {

    }

    public AnuncioSazonalidade(Anuncio anuncio, Sazonalidade sazonalidade) {
        this.anuncio = anuncio;
        this.sazonalidade = sazonalidade;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnuncioSazonalidade that = (AnuncioSazonalidade) o;
        return Objects.equals(anuncio, that.anuncio) &&
                sazonalidade == that.sazonalidade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(anuncio, sazonalidade);
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Sazonalidade getSazonalidade() {
        return sazonalidade;
    }

    public void setSazonalidade(Sazonalidade sazonalidade) {
        this.sazonalidade = sazonalidade;
    }

}
