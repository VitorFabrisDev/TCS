package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Produto;
import com.tcs_senac.ruralfacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto obterProdutoPorId(Long id) throws NotFoundException {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            return produto.get();
        } else {
            throw new NotFoundException("Produto não encontrado");
        }
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) throws NotFoundException {
        Produto produtoExistente = obterProdutoPorId(id);
        produtoExistente.setNomeProduto(produtoAtualizado.getNomeProduto());

        return produtoRepository.save(produtoExistente);

    }
}
