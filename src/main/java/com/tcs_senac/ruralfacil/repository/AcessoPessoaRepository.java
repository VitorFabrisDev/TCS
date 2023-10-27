package com.tcs_senac.ruralfacil.repository;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AcessoPessoaRepository extends JpaRepository<AcessoPessoa, Long> {

    Optional<AcessoPessoa> findByLogin(String login);
    Boolean existsByLogin(String login);


}
