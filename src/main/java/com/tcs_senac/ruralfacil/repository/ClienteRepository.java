package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
