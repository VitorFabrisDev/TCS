package com.tcs_senac.ruralfacil.controller;


import com.tcs_senac.ruralfacil.exception.AnuncioNotFoundException;
import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/anuncio")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping("/cadastrar")
    public Anuncio cadastrarAnuncio(@Valid @RequestBody Anuncio anuncio) {
        return anuncioService.cadastrarAnuncio(anuncio);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Anuncio> alistarAnuncios() {

        return anuncioService.listarAnuncios();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Anuncio obterAgricultorPessoa(@PathVariable Long id) throws AnuncioNotFoundException {
        return anuncioService.obterAnuncioPorId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Anuncio atualizarAgricultor(@PathVariable Long id, @RequestBody Anuncio anuncio) throws AnuncioNotFoundException {
        return anuncioService.atualizarAnuncio(id, anuncio);
    }
}
