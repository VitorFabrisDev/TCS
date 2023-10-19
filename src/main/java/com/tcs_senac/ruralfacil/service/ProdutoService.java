package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.PessoaNotFoundException;
import com.tcs_senac.ruralfacil.exception.ProdutoNotFoundException;
import com.tcs_senac.ruralfacil.model.Produto;
import com.tcs_senac.ruralfacil.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto obterProdutoPorId(Long id) throws ProdutoNotFoundException {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            return produto.get();
        } else {
            throw new ProdutoNotFoundException("Produto não encontrado");
        }
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) throws ProdutoNotFoundException {
        Produto produtoExistente = obterProdutoPorId(id);
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());

        return produtoRepository.save(produtoExistente);

    }
}
