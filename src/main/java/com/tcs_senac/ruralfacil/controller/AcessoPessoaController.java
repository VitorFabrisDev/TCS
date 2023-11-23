package com.tcs_senac.ruralfacil.controller;


import com.tcs_senac.ruralfacil.dto.AcessoPessoaDto;
import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.service.AcessoPessoaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "AcessoPessoa API", tags = { "AcessoPessoa" })
@RestController
@RequestMapping("/api/acessopessoa")
public class AcessoPessoaController extends AbstractController {

    @Autowired
    private AcessoPessoaService acessoPessoaService;

    @PostMapping
    public AcessoPessoaDto cadastrarAcessoPessoa(@Valid @RequestBody AcessoPessoaDto acessoPessoaDTO) {
        AcessoPessoa acessoPessoa = acessoPessoaService.cadastrarAcessoPessoa(acessoPessoaDTO.toEntity());
        return AcessoPessoaDto.fromEntity(acessoPessoa);
    }

    @GetMapping
    //@PreAuthorize("hasRole('Agricultor') and hasRole('Cliente') and hasRole('Administrador')")
    public List<AcessoPessoaDto> listarAcessos() {
        List<AcessoPessoa> acessos = acessoPessoaService.listarAcessos();
        return acessos.stream().map(AcessoPessoaDto::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('Agricultor') and hasRole('Cliente') and hasRole('Administrador')")
    public AcessoPessoaDto obterAcessoPessoaPorId(@PathVariable Long id) throws NotFoundException {
        AcessoPessoa acessoPessoa = acessoPessoaService.obterAcessoPessoaPorId(id);
        return AcessoPessoaDto.fromEntity(acessoPessoa);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AcessoPessoaDto atualizarAcessoPessoa(@PathVariable Long id, @RequestBody AcessoPessoaDto acessoPessoaDTO) throws NotFoundException {
        AcessoPessoa acessoPessoa = acessoPessoaService.atualizarAcessoPessoa(id, acessoPessoaDTO.toEntity());
        return AcessoPessoaDto.fromEntity(acessoPessoa);
    }
}
