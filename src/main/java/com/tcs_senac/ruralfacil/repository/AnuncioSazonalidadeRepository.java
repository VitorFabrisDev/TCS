package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.model.AnuncioSazonalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioSazonalidadeRepository extends JpaRepository<AnuncioSazonalidade, Long> {


}
