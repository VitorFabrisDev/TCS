package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.enterpise.CustomQuerydslPredicateExecutor;
import com.tcs_senac.ruralfacil.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> , CustomQuerydslPredicateExecutor<Anuncio> {
}
