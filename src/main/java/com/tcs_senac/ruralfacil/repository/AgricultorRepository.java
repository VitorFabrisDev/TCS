package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Agricultor;

import java.util.Optional;

public class AgricultorRepository {

    public Agricultor save(Agricultor agricultor) {
        return agricultor;
    }

    public Optional<Agricultor> findById(Long id) {
        return null;
    }
}
