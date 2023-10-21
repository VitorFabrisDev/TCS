package com.tcs_senac.ruralfacil.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EnderecoPessoa {

    @ManyToOne
    @JoinColumn(name = "idEndereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = true)
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "idAgricultor", nullable = true)
    private Agricultor agricultor;


}
