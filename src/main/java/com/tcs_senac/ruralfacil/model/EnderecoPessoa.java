package com.tcs_senac.ruralfacil.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class EnderecoPessoa extends EntityId{



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
