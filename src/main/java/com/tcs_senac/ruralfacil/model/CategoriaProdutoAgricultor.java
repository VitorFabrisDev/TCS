package com.tcs_senac.ruralfacil.model;

import javax.persistence.*;

@Entity
public class CategoriaProdutoAgricultor {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    @ManyToOne
    @JoinColumn(
            name = "idProduto"
    )
    private Produto produto;

    @ManyToOne
    @JoinColumn(
            name = "idCategoria"
    )
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(
            name = "idAgricultor"
    )
    private Agricultor agricultor;
}
