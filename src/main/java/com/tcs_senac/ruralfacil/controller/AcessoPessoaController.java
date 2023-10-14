package com.tcs_senac.ruralfacil.controller;


import com.tcs_senac.ruralfacil.exception.AcessoPessoaNotFoundException;
import com.tcs_senac.ruralfacil.exception.AgricultorNotFoundException;
import com.tcs_senac.ruralfacil.exception.PessoaNotFoundException;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.service.AcessoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acessopessoa")
public class AcessoPessoaController {
    @Autowired
    private AcessoPessoaService acessoPessoaService;

    @PostMapping("/cadastrar")
    public AcessoPessoa acessoPessoa(@Valid  @RequestBody AcessoPessoa acessoPessoa) {
        return acessoPessoaService.cadastrarAcessoPessoa(acessoPessoa);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AcessoPessoa obterAcessoPessoaPorId(@PathVariable(value = "id") Long id) throws AcessoPessoaNotFoundException {
        return (AcessoPessoa) acessoPessoaService.obterAcessoPessoaPorId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AcessoPessoa atualizarAcessoPessoa(@PathVariable(value = "id") Long id, @RequestBody AcessoPessoa acessoPessoa) throws PessoaNotFoundException, AgricultorNotFoundException {
        return acessoPessoaService.atualizarAcessoPessoa(id, acessoPessoa);
    }
}
