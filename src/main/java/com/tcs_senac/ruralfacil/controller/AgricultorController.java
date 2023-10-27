package com.tcs_senac.ruralfacil.controller;
import com.tcs_senac.ruralfacil.dto.AgricultorDto;
import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.service.AgricultorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/agricultor")
public class AgricultorController extends AbstractController {

    @Autowired
    private AgricultorService agricultorService;

    @PostMapping
    public AgricultorDto cadastrarAgricultor(@Valid @RequestBody AgricultorDto agricultorDTO) {
        Agricultor agricultor = agricultorService.cadastrarAgricultorPessoa(agricultorDTO.toEntity());
        return AgricultorDto.fromEntity(agricultor);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AgricultorDto> listarAgricultores() {
        List<Agricultor> agricultores = agricultorService.listarAgricultores();
        return agricultores.stream().map(AgricultorDto::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AgricultorDto obterAgricultorPorId(@PathVariable Long id) throws NotFoundException {
        Agricultor agricultor = agricultorService.obterAgricultorPessoa(id);
        return AgricultorDto.fromEntity(agricultor);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AgricultorDto atualizarAgricultor(@PathVariable Long id, @RequestBody AgricultorDto agricultorDTO) throws NotFoundException {
        Agricultor agricultor = agricultorService.atualizarAgricultor(id, agricultorDTO.toEntity());
        return AgricultorDto.fromEntity(agricultor);
    }
}
