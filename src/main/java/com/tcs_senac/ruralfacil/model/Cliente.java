package com.tcs_senac.ruralfacil.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Cliente extends Pessoa{

    public Cliente(AcessoPessoa acessoPessoa, Endereco endereco, String cpf, String nome, LocalDateTime dataNascimento,  String whatsApp) {
        super(acessoPessoa, endereco, cpf, nome, dataNascimento, whatsApp);
    }


    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    public Cliente() {
        super();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

