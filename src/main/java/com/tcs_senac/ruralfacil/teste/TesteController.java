package com.tcs_senac.ruralfacil.teste;

import com.tcs_senac.ruralfacil.Enum.Categoria;
import com.tcs_senac.ruralfacil.model.Produto;
import com.tcs_senac.ruralfacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tcs_senac.ruralfacil.Enum.Categoria.MUDAS_SEMENTES;

@RestController
public class TesteController {

    @Autowired
    public ProdutoRepository produtoRepository;

    @GetMapping("/teste")
    public String teste() {
        Produto produto = new Produto();
        Categoria categoria = MUDAS_SEMENTES;
        produto.setDescricao("Semente Alface Americana");
        produto.setCategoria(categoria);
        produto = produtoRepository.save(produto);

        return "OK";
    }

}
