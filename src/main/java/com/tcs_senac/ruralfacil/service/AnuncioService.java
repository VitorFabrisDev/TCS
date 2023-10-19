package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.AnuncioNotFoundException;
import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;

    @Autowired
    public AnuncioService(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    public Anuncio cadastrarAnuncio(Anuncio anuncio) {
        return anuncioRepository.save(anuncio);
    }

    public List<Anuncio> listarAnuncios() {
        return anuncioRepository.findAll();
    }

    public Anuncio obterAnuncioPorId(Long id) throws AnuncioNotFoundException {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        if (anuncio.isPresent()) {
            return anuncio.get();
        } else {
            throw new AnuncioNotFoundException("Anúncio não encontrado");
        }
    }

    public Anuncio atualizarAnuncio(Long id, Anuncio anuncioAtualizado) throws AnuncioNotFoundException {
        Anuncio anuncioExistente = obterAnuncioPorId(id);
        anuncioExistente.setCategoriaProdutoAgricultor(anuncioAtualizado.getCategoriaProdutoAgricultor());
        anuncioExistente.setDescricao(anuncioAtualizado.getDescricao());
        anuncioExistente.setOrganico(anuncioAtualizado.getOrganico());
        anuncioExistente.setClassificacao(anuncioAtualizado.getClassificacao());
        anuncioExistente.setValor(anuncioAtualizado.getValor());
        anuncioExistente.setFoto1(anuncioAtualizado.getFoto1());
        anuncioExistente.setFoto2(anuncioAtualizado.getFoto2());
        anuncioExistente.setFoto3(anuncioAtualizado.getFoto3());
        anuncioExistente.setFoto4(anuncioAtualizado.getFoto4());
        anuncioExistente.setFoto5(anuncioAtualizado.getFoto5());
        return anuncioRepository.save(anuncioExistente);

    }
}
