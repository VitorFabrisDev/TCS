package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Agricultor;

import java.util.Optional;

public interface AgricultorRepository {

    public default Agricultor save(Agricultor agricultor) {

        return agricultor;
    }

    public default Optional<Agricultor> findById(Long id) {

        return null;
    }
}
