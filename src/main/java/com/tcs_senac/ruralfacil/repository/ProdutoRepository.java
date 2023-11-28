package com.tcs_senac.ruralfacil.repository;

import com.tcs_senac.ruralfacil.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long> {
    Produto findByNomeProduto(String nomeProduto);
}
