package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository {

    public default Pessoa save(Pessoa pessoa) {
        return pessoa;
    }

    public default List<Pessoa> findAll() {
        return null;
    }


    public default Optional<Pessoa> findById(Long id) {
        return null;
    }
}
