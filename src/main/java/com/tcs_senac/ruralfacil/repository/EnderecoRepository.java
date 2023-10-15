package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
