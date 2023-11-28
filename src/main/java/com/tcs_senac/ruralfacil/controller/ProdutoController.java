package com.tcs_senac.ruralfacil.controller;

import com.tcs_senac.ruralfacil.dto.ProdutoDto; // Importe sua classe DTO aqui
import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Produto;
import com.tcs_senac.ruralfacil.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Produto API", tags = { "Produto" })
@RestController
@RequestMapping("/api/produto")
public class ProdutoController extends AbstractController {

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation(value = "Cadastrar um novo produto")
    @PostMapping
    public Produto cadastrarProduto(@Valid @RequestBody ProdutoDto produtoDTO) {
        // Mapeie o ProdutoDTO para a entidade Produto antes de chamar o serviço
        Produto produto = mapProdutoDTOToEntity(produtoDTO);
        return produtoService.cadastrarProduto(produto);
    }

    @ApiOperation(value = "Listar todos os produtos")
    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @ApiOperation(value = "Obter um produto por ID")
    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Produto obterProdutoPorId(@PathVariable Long id) throws NotFoundException {
        return produtoService.obterProdutoPorId(id);
    }

    @ApiOperation(value = "Atualizar um produto por ID ")
    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDTO) throws NotFoundException {
        Produto produto = mapProdutoDTOToEntity(produtoDTO);
        return produtoService.atualizarProduto(id, produto);
    }

    private Produto mapProdutoDTOToEntity(ProdutoDto produtoDTO) {
        Produto produto = new Produto();
        produto.setNomeProduto(produtoDTO.getNomeProduto());
        produto.setNomeProduto(produtoDTO.getNomeProduto());

        return produto;
    }
}