package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.dto.AnuncioClienteClassificacaoDto;
import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.AnuncioClienteClassificacao;
import com.tcs_senac.ruralfacil.repository.AnuncioClienteClassificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnuncioClienteClassificacaoService {

    private final AnuncioClienteClassificacaoRepository anuncioClienteClassificacaoRepository;

    @Autowired
    public AnuncioClienteClassificacaoService(AnuncioClienteClassificacaoRepository anuncioClienteClassificacaoRepository) {
        this.anuncioClienteClassificacaoRepository = anuncioClienteClassificacaoRepository;
    }

    public AnuncioClienteClassificacao cadastrarAnuncioClienteClassificacao(AnuncioClienteClassificacao anuncioClienteClassificacao) {
        return anuncioClienteClassificacaoRepository.save(anuncioClienteClassificacao);
    }


    public AnuncioClienteClassificacao obterAnuncioClienteClassificacaoPorId(Long id) throws NotFoundException {
        Optional<AnuncioClienteClassificacao> anuncioClienteClassificacao = anuncioClienteClassificacaoRepository.findById(id);
        if (anuncioClienteClassificacao.isPresent()) {
            return anuncioClienteClassificacao.get();
        } else {
            throw new NotFoundException("Classificação de anúncio não encontrado");
        }
    }

    public AnuncioClienteClassificacao atualizarAnuncioClienteClassificacao(Long id, AnuncioClienteClassificacao anuncioClienteClassificacao) throws NotFoundException {
        AnuncioClienteClassificacao anuncioClienteClassificacaoExistente = obterAnuncioClienteClassificacaoPorId(id);
        return null;


    }

    public AnuncioClienteClassificacao obterPorIdAnuncioIdCliente(long anuncioId, long clienteId) {
        return anuncioClienteClassificacaoRepository.findByAnuncioIdAndClienteId(anuncioId, clienteId);
    }
}
