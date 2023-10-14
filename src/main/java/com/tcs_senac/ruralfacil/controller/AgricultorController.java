package com.tcs_senac.ruralfacil.controller;
import com.tcs_senac.ruralfacil.exception.AgricultorNotFoundException;
import com.tcs_senac.ruralfacil.exception.PessoaNotFoundException;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.service.AgricultorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/agricultor")
public class AgricultorController {
    @Autowired
    private AgricultorService agricultorService;

    @PostMapping("/cadastrar")
    public Agricultor cadastrarAgricultorPessoa(@Valid  @RequestBody Agricultor agricultor) {
        return agricultorService.cadastrarAgricultorPessoa(agricultor);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Agricultor obterAgricultorPessoa(@PathVariable Long id) throws AgricultorNotFoundException {
        return (Agricultor) agricultorService.obterAgricultorPessoa(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Agricultor atualizarAgricultor(@PathVariable Long id, @RequestBody Agricultor agricultor) throws PessoaNotFoundException, AgricultorNotFoundException {
        return agricultorService.atualizarAgricultor(id, agricultor);
    }
}
