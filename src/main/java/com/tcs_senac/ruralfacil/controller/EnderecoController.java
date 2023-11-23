package com.tcs_senac.ruralfacil.controller;


import com.tcs_senac.ruralfacil.dto.EnderecoDto;
import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Endereco;
import com.tcs_senac.ruralfacil.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Endereço API", tags = { "Endereço" })
@RestController
@RequestMapping("/api/endereco")
public class EnderecoController extends AbstractController {

    @Autowired
    private EnderecoService enderecoService;

    @ApiOperation(value = "Cadastrar um novo endereço")
    @PostMapping
    public Endereco cadastrarEndereco(@Valid @RequestBody EnderecoDto enderecoDto) {
        // Mapeie o EnderecoDTO para a entidade Endereco antes de chamar o serviço
        Endereco endereco = mapEnderecoDTOToEntity(enderecoDto);
        return enderecoService.cadastrarEndereco(endereco);
    }

    @ApiOperation(value = "Listar todos os endereços")
    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<Endereco> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    @ApiOperation(value = "Obter um endereço por ID")
    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Endereco obterEnderecoPorId(@PathVariable(value = "id") Long id) throws NotFoundException {
        return enderecoService.obterEnderecoPorId(id);
    }

    @ApiOperation(value = "Atualizar um endereço por ID")
    @PutMapping("/{id}")
   // @PreAuthorize("hasRole('ADMIN')")
    public Endereco atualizarEndereco(@PathVariable(value ="id") Long id, @RequestBody EnderecoDto enderecoDto) throws NotFoundException {
        Endereco endereco = mapEnderecoDTOToEntity(enderecoDto);
        return enderecoService.atualizarEndereco(id, endereco);
    }

    private Endereco mapEnderecoDTOToEntity(EnderecoDto enderecoDto) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDto.getLogradouro());
        endereco.setMunicipio(enderecoDto.getMunicipio());
        return endereco;
    }
}