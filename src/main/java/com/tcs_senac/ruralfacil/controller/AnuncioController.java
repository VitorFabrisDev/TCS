package com.tcs_senac.ruralfacil.controller;

import com.tcs_senac.ruralfacil.controller.AbstractController;
import com.tcs_senac.ruralfacil.dto.AnuncioDto;
import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/anuncio")
public class AnuncioController extends AbstractController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    public AnuncioDto cadastrarAnuncio(@Valid @RequestBody AnuncioDto anuncioDto) {
        Anuncio anuncio = anuncioService.cadastrarAnuncio(anuncioDto.toEntity());
        return AnuncioDto.fromEntity(anuncio);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AnuncioDto> listarAnuncios() {
        List<Anuncio> anuncios = anuncioService.listarAnuncios();
        return anuncios.stream().map(AnuncioDto::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public AnuncioDto obterAnuncioPorId(@PathVariable Long id) throws NotFoundException {
        Anuncio anuncio = anuncioService.obterAnuncioPorId(id);
        return AnuncioDto.fromEntity(anuncio);
    }

    @PutMapping("/{id}")
   // @PreAuthorize("hasRole('ADMIN')")
    public AnuncioDto atualizarAnuncio(@PathVariable Long id, @RequestBody AnuncioDto anuncioDTO) throws NotFoundException {
        Anuncio anuncio = anuncioService.atualizarAnuncio(id, anuncioDTO.toEntity());
        return AnuncioDto.fromEntity(anuncio);
    }
}