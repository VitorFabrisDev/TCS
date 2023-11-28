package com.tcs_senac.ruralfacil.dto;

import com.tcs_senac.ruralfacil.model.Enum.Categoria;
import com.tcs_senac.ruralfacil.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {

    private Long id;
    //private String nome;
    private String nomeProduto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String descricao) {
        this.nomeProduto = nomeProduto;
    }


    public static ProdutoDto fromEntity(Produto produto) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(produto.getId());
        dto.setNomeProduto(produto.getNomeProduto());
        return dto;
    }

    public Produto toEntity() {
        Produto produto = new Produto();
        produto.setId(this.getId());
        produto.setNomeProduto(this.getNomeProduto());
        return produto;
    }

    public static List<ProdutoDto> fromEntity(List<Produto> produtos) {
        return produtos.stream().map(produto -> fromEntity(produto)).collect(Collectors.toList());
    }

    public static Page<ProdutoDto> fromEntity(Page<Produto> produtos) {
        List<ProdutoDto> produtosFind = produtos.stream().map(produto -> fromEntity(produto)).collect(Collectors.toList());
        Page<ProdutoDto> produtosDTO = new PageImpl<>(produtosFind, produtos.getPageable(), produtos.getTotalElements());
        return produtosDTO;
    }

}
