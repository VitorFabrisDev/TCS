package com.tcs_senac.ruralfacil.controller;

import com.tcs_senac.ruralfacil.model.Pessoa;
import com.tcs_senac.ruralfacil.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public Pessoa cadastrarPessoa(@RequestBody Pessoa pessoa) {
        return pessoaService.cadastrarPessoa(pessoa);
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    @GetMapping("/{id}")
    public Pessoa obterPessoaPorId(@PathVariable Long id) {
        return pessoaService.obterPessoaPorId(id);
    }

    @PutMapping("/{id}")
    public Pessoa atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return pessoaService.atualizarPessoa(id, pessoa);
    }

}
