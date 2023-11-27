package com.tcs_senac.ruralfacil.controller;
import com.tcs_senac.ruralfacil.dto.AgricultorDto;
import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Cliente;
import com.tcs_senac.ruralfacil.model.Endereco;
import com.tcs_senac.ruralfacil.service.AcessoPessoaService;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.service.AgricultorService;
import com.tcs_senac.ruralfacil.service.EnderecoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Agricultor API", tags = { "Agricultor" })
@RestController
@RequestMapping("/api/agricultor")
public class AgricultorController extends AbstractController {

    @Autowired
    private AgricultorService agricultorService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private AcessoPessoaService acessoPessoaService;


    @PostMapping
    public Agricultor cadastrarAgricultor(@Valid @RequestBody AgricultorDto agricultorDTO) {

        AcessoPessoa acessoPessoa = acessoPessoaService.obterAcessoPessoaByLogin(agricultorDTO.getAcessoPessoa().getLogin());

        Agricultor agricultor = mapAgricultorDTOToEntity(agricultorDTO);

        Endereco endereco = agricultor.getEndereco();
        if (endereco != null) {
            enderecoService.cadastrarEndereco(endereco);
        }

        agricultor.setAcessoPessoa(acessoPessoa);

        return agricultorService.cadastrarAgricultor(agricultor);
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<AgricultorDto> listarAgricultores() {
        List<Agricultor> agricultores = agricultorService.listarAgricultores();
        return agricultores.stream().map(AgricultorDto::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{idacessopessoa}")
   // @PreAuthorize("hasRole('ADMIN')")
    public AgricultorDto obterAgricultorPorAcessoPessoa(@PathVariable Long idacessopessoa) throws NotFoundException {
        Agricultor agricultor = agricultorService.obterAgricultorPorAcessoPessoa(idacessopessoa);
        return AgricultorDto.fromEntity(agricultor);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Agricultor atualizarAgricultor(@PathVariable Long id, @RequestBody AgricultorDto agricultorDTO) throws NotFoundException {

        Agricultor agricultor = mapAgricultorDTOToEntity(agricultorDTO);

        Endereco endereco = agricultor.getEndereco();

        Agricultor agricultorExistente = agricultorService.obterAgricultorPorId(id);


        if (endereco != null) {

            enderecoService.atualizarEndereco(agricultorExistente.getEndereco().getId(),endereco);
        }
        AcessoPessoa acessoPessoa = agricultor.getAcessoPessoa();
        if(acessoPessoa != null){

            acessoPessoaService.atualizarAcessoPessoa(agricultorExistente.getAcessoPessoa().getId(),acessoPessoa);
        }

        return agricultorService.atualizarAgricultor(id, agricultor);
    }

    private Agricultor mapAgricultorDTOToEntity(AgricultorDto agricultorDTO) {
        Agricultor agricultor = new Agricultor(agricultorDTO.getAcessoPessoa(), agricultorDTO.getEndereco(), agricultorDTO.getCpf(), agricultorDTO.getNome(), agricultorDTO.getDataNascimento(),  agricultorDTO.getWhatsApp(), agricultorDTO.getInscricaoEstadual(), agricultorDTO.getCaf(), agricultorDTO.getOrganico(), agricultorDTO.getAtivo());
        return agricultor;
    }
}
