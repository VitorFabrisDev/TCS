package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.AnuncioClienteClassificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioClienteClassificacaoRepository extends JpaRepository<AnuncioClienteClassificacao, Long> {
    AnuncioClienteClassificacao findByAnuncioIdAndClienteId(long anuncioId, long clienteId);
}
