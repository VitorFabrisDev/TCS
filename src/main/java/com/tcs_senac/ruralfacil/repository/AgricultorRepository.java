package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgricultorRepository extends JpaRepository<Agricultor, Long> {


    boolean existsByCpf(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Long id);


    Optional<Agricultor> findByCpf(String cpf);

}
