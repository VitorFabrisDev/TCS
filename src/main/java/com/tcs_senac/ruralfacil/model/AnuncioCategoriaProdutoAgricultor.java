package com.tcs_senac.ruralfacil.model;

import com.tcs_senac.ruralfacil.Enum.Categoria;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AnuncioCategoriaProdutoAgricultor extends EntityId{


    @ManyToOne
    @JoinColumn(
            name = "idAnuncio"
    )
    private Anuncio anuncio;

    @ManyToOne
    @JoinColumn(
            name = "idProduto"
    )
    private Produto produto;


    @Column(
            name= "categoria"
    )
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(
            name = "idAgricultor"
    )
    private Agricultor agricultor;

}
