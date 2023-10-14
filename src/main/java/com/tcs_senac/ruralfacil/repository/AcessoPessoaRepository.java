package com.tcs_senac.ruralfacil.repository;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AcessoPessoaRepository extends JpaRepository<AcessoPessoa, Long> {
}
