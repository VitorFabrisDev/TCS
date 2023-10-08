package com.tcs_senac.ruralfacil.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoSazonalidade {

    @ManyToOne
    @JoinColumn(
            name = "idProduto"
    )
    private Produto produto;
    @ManyToOne
    @JoinColumn(
            name = "idSazonalidade"
    )
    private Sazonalidade sazonalidade;

}
