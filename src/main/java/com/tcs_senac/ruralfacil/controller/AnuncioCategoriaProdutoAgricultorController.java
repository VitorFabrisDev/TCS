package com.tcs_senac.ruralfacil.controller;

import com.tcs_senac.ruralfacil.model.AnuncioCategoriaProdutoAgricultor;
import com.tcs_senac.ruralfacil.service.AnuncioCategoriaProdutoAgricultorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/anunciocategoriaprodutoagricultor")
public class AnuncioCategoriaProdutoAgricultorController extends AbstractController{

    @Autowired
    private AnuncioCategoriaProdutoAgricultorService anuncioCategoriaProdutoAgricultorService;

    @PostMapping("/cadastrar")
    public AnuncioCategoriaProdutoAgricultor cadastrarAnuncio(@Valid @RequestBody AnuncioCategoriaProdutoAgricultor anuncioCategoriaProdutoAgricultor) {
        return anuncioCategoriaProdutoAgricultorService.cadastrarAnuncioCategoriaProdutoAgricultor(anuncioCategoriaProdutoAgricultor);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AnuncioCategoriaProdutoAgricultor> listarAnuncioCategoriaProdutoAgricultor() {

        return anuncioCategoriaProdutoAgricultorService.listarAnuncioCategoriaProdutoAgricultorService();
    }


}
