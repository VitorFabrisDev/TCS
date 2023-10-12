package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Pessoa;

import java.util.List;
import java.util.Optional;

public class PessoaRepository {

    public Pessoa save(Pessoa pessoa) {
        return pessoa;
    }

    public List<Pessoa> findAll() {
        return null;
    }


    public Optional<Pessoa> findById(Long id) {
        return null;
    }
}
