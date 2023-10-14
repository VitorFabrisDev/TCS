package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgricultorRepository extends JpaRepository<Agricultor, Long> {

}
