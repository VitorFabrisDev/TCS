package com.tcs_senac.ruralfacil.controller;


import com.tcs_senac.ruralfacil.exception.AgricultorNotFoundException;
import com.tcs_senac.ruralfacil.exception.ProdutoNotFoundException;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.model.Produto;
import com.tcs_senac.ruralfacil.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    @PostMapping("/cadastrar")
    public Produto cadastrarAgricultorPessoa(@Valid @RequestBody Produto produto) {
        return produtoService.cadastrarProduto(produto);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Produto> listarProdutos() {

        return produtoService.listarProdutos();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Produto obterProdutoPorId(@PathVariable Long id) throws ProdutoNotFoundException {
        return produtoService.obterProdutoPorId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) throws ProdutoNotFoundException {
        return produtoService.atualizarProduto(id, produto);
    }
}
