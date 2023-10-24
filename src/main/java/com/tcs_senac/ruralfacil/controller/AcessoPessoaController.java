package com.tcs_senac.ruralfacil.controller;


import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.service.AcessoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/acessopessoa")
public class AcessoPessoaController extends AbstractController{
    @Autowired
    private AcessoPessoaService acessoPessoaService;

    @PostMapping("/cadastrar")
    public AcessoPessoa acessoPessoa(@Valid @RequestBody AcessoPessoa acessoPessoa) {
        return acessoPessoaService.cadastrarAcessoPessoa(acessoPessoa);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AcessoPessoa> listarAcessos() {

        return acessoPessoaService.listarAcessos();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AcessoPessoa obterAcessoPessoaPorId(@PathVariable(value = "id") Long id) throws NotFoundException {
        return acessoPessoaService.obterAcessoPessoaPorId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AcessoPessoa atualizarAcessoPessoa(@PathVariable(value = "id") Long id, @RequestBody AcessoPessoa acessoPessoa) throws NotFoundException {
        return acessoPessoaService.atualizarAcessoPessoa(id, acessoPessoa);
    }
}
