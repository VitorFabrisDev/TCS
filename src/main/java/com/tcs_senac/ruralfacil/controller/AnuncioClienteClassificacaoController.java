package com.tcs_senac.ruralfacil.controller;

import com.tcs_senac.ruralfacil.dto.AnuncioClienteClassificacaoDto;
import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.*;
import com.tcs_senac.ruralfacil.service.AnuncioClienteClassificacaoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(value = "AnuncioCliente API", tags = { "Anuncio Cliente" })
@RestController
@RequestMapping("/api/anuncioCliente")
public class AnuncioClienteClassificacaoController {
    @Autowired
    private AnuncioClienteClassificacaoService anuncioClienteClassificacaoService;

    @PostMapping
    public AnuncioClienteClassificacaoDto cadastrarAnuncioCLiente(@Valid @RequestBody AnuncioClienteClassificacaoDto anuncioClienteClassificacaoDto) {

        AnuncioClienteClassificacao anuncioClienteClassificacao = mapAnuncioClienteClassificacaoDTOToEntity(anuncioClienteClassificacaoDto);

        AnuncioClienteClassificacao existente = anuncioClienteClassificacaoService.obterPorIdAnuncioIdCliente(
                anuncioClienteClassificacao.getAnuncio().getId(),
                anuncioClienteClassificacao.getCliente().getId()
        );

        if (existente != null) {
            existente.setDtUltAcesso(anuncioClienteClassificacao.getDtUltAcesso());
            existente.setQtdAcesso(anuncioClienteClassificacao.getQtdAcesso());
            existente.setClassificacao(anuncioClienteClassificacao.getClassificacao());
            existente.setQtdInteracao(anuncioClienteClassificacao.getQtdInteracao());

            anuncioClienteClassificacaoService.atualizarAnuncioClienteClassificacao(existente.getId(), existente);

            return AnuncioClienteClassificacaoDto.fromEntity(existente);
        } else {
            anuncioClienteClassificacaoService.cadastrarAnuncioClienteClassificacao(anuncioClienteClassificacao);
            return AnuncioClienteClassificacaoDto.fromEntity(anuncioClienteClassificacao);
        }
    }


    @GetMapping("/{idCliente}/{idAnuncio}")
    public AnuncioClienteClassificacaoDto obterAnuncioClientePorId(@PathVariable Long idCliente, @PathVariable Long idAnuncio) throws NotFoundException {
        AnuncioClienteClassificacao anuncioClienteClassificacao = anuncioClienteClassificacaoService.obterPorIdAnuncioIdCliente(idAnuncio, idCliente);

        if (anuncioClienteClassificacao == null) {
            throw new NotFoundException("AnuncioClienteClassificacao não encontrado para o ID do cliente: " + idCliente + " e ID do anúncio: " + idAnuncio);
        }

        return AnuncioClienteClassificacaoDto.fromEntity(anuncioClienteClassificacao);
    }


    private AnuncioClienteClassificacao mapAnuncioClienteClassificacaoDTOToEntity(AnuncioClienteClassificacaoDto dto) {
        AnuncioClienteClassificacao anuncioClienteClassificacao = new AnuncioClienteClassificacao();

        anuncioClienteClassificacao.setDtUltAcesso(dto.getDtUltAcesso());
        anuncioClienteClassificacao.setQtdAcesso(dto.getQtdAcesso());
        anuncioClienteClassificacao.setClassificacao(dto.getClassificacao());
        anuncioClienteClassificacao.setQtdInteracao(dto.getQtdInteracao());

        Cliente cliente = new Cliente();
        cliente.setId(dto.getCliente().getId());

        Anuncio anuncio = new Anuncio();
        anuncio.setId(dto.getAnuncio().getId());


        anuncioClienteClassificacao.setCliente(cliente);
        anuncioClienteClassificacao.setAnuncio(anuncio);

        return anuncioClienteClassificacao;
    }
}
