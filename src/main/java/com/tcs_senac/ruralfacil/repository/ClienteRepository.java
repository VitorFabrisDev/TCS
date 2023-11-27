package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    


    boolean existsByCpfAndIdNot(String cpf, Long id);

    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findClienteByAcessoPessoa(Optional<AcessoPessoa> acessoPessoa);
}
