package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Long id);
    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByCpf(String cpf);
}
