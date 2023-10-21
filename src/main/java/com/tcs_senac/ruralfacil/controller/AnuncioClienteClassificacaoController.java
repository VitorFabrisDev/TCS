package com.tcs_senac.ruralfacil.controller;


import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.AnuncioClienteClassificacao;
import com.tcs_senac.ruralfacil.service.AnuncioClienteClassificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/anuncioclienteclassificacao")
public class AnuncioClienteClassificacaoController {

    @Autowired
    private AnuncioClienteClassificacaoService anuncioClienteClassificacaoService;

    @PostMapping("/cadastrar")
    public AnuncioClienteClassificacao cadastrarAnuncioClienteClassificacao(@Valid @RequestBody AnuncioClienteClassificacao anuncioClienteClassificacao) {
        return anuncioClienteClassificacaoService.cadastrarAnuncioClienteClassificacao(anuncioClienteClassificacao);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AnuncioClienteClassificacao> listarAnuncioClienteClassificacao() {

        return anuncioClienteClassificacaoService.listarAgricultores();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AnuncioClienteClassificacao obterAnuncioClienteClassificacaoPorId(@PathVariable Long id) throws NotFoundException {
        return anuncioClienteClassificacaoService.obterAnuncioClienteClassificacaoPorId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AnuncioClienteClassificacao atualizarAnuncioClienteClassificacao(@PathVariable Long id, @RequestBody AnuncioClienteClassificacao anuncioClienteClassificacao) throws NotFoundException {
        return anuncioClienteClassificacaoService.atualizarAnuncioClienteClassificacao(id, anuncioClienteClassificacao);
    }

}
