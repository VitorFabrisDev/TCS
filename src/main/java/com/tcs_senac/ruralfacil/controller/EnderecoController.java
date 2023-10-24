package com.tcs_senac.ruralfacil.controller;


import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Endereco;
import com.tcs_senac.ruralfacil.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController extends AbstractController{

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/cadastrar")
    public Endereco endereco(@Valid @RequestBody Endereco endereco) {
        return enderecoService.cadastrarEndereco(endereco);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Endereco> listarEndereco() {

        return enderecoService.listarEnderecos();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Endereco obterEnderecoPorId(@PathVariable(value = "id") Long id) throws NotFoundException {

        return enderecoService.obterEnderecoPorId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Endereco atualizarEndereco(@PathVariable(value ="id") Long id, @RequestBody Endereco endereco) throws NotFoundException {
        return enderecoService.atualizarEndereco(id, endereco);
    }
}
