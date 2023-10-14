package com.tcs_senac.ruralfacil.controller;

import com.tcs_senac.ruralfacil.exception.PessoaNotFoundException;
import com.tcs_senac.ruralfacil.model.Pessoa;
import com.tcs_senac.ruralfacil.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public Pessoa cadastrarPessoa(@Valid @RequestBody Pessoa pessoa) {

        return pessoaService.cadastrarPessoa(pessoa);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Pessoa> listarPessoas() {

        return pessoaService.listarPessoas();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Pessoa obterPessoaPorId(@PathVariable(value = "id") Long id) throws PessoaNotFoundException {

        return pessoaService.obterPessoaPorId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Pessoa atualizarPessoa(@PathVariable(value ="id") Long id, @RequestBody Pessoa pessoa) throws PessoaNotFoundException {
        return pessoaService.atualizarPessoa(id, pessoa);
    }

}
