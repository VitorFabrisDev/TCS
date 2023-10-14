package com.tcs_senac.ruralfacil.repository;


import com.tcs_senac.ruralfacil.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
