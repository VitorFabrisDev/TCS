package com.tcs_senac.ruralfacil.model;


import javax.persistence.*;
import java.util.Date;


@Entity
public class Cliente extends Pessoa{

    public Cliente(String cpf, String nome, Date dataNascimento, String email, String whatsApp) {
        super(cpf, nome, dataNascimento, email, whatsApp);
    }



    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

