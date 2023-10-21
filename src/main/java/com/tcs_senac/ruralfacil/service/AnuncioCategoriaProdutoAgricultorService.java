package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.model.AnuncioCategoriaProdutoAgricultor;
import com.tcs_senac.ruralfacil.repository.AnuncioCategoriaProdutoAgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioCategoriaProdutoAgricultorService {


    private final AnuncioCategoriaProdutoAgricultorRepository anuncioCategoriaProdutoAgricultorRepository;
    @Autowired
    public AnuncioCategoriaProdutoAgricultorService(AnuncioCategoriaProdutoAgricultorRepository anuncioCategoriaProdutoAgricultorRepository) {
        this.anuncioCategoriaProdutoAgricultorRepository = anuncioCategoriaProdutoAgricultorRepository;
    }

    public AnuncioCategoriaProdutoAgricultor cadastrarAnuncioCategoriaProdutoAgricultor(AnuncioCategoriaProdutoAgricultor anuncioCategoriaProdutoAgricultor) {
        return anuncioCategoriaProdutoAgricultorRepository.save(anuncioCategoriaProdutoAgricultor);
    }

    public List<AnuncioCategoriaProdutoAgricultor> listarAnuncioCategoriaProdutoAgricultorService() {
        return anuncioCategoriaProdutoAgricultorRepository.findAll();
    }
}
