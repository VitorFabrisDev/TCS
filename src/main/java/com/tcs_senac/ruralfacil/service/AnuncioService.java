package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.model.AnuncioSazonalidade;
import com.tcs_senac.ruralfacil.model.Produto;
import com.tcs_senac.ruralfacil.repository.AnuncioRepository;
import com.tcs_senac.ruralfacil.repository.AnuncioSazonalidadeRepository;
import com.tcs_senac.ruralfacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    private final AnuncioSazonalidadeRepository anuncioSazonalidadeRepository;

    private final ProdutoRepository produtoRepository;

    @Autowired
    public AnuncioService(AnuncioRepository anuncioRepository, AnuncioSazonalidadeRepository anuncioSazonalidadeRepository, ProdutoRepository produtoRepository) {
        this.anuncioRepository = anuncioRepository;
        this.anuncioSazonalidadeRepository = anuncioSazonalidadeRepository;
        this.produtoRepository = produtoRepository;
    }

    public Anuncio cadastrarAnuncio(Anuncio anuncio) {
        return anuncioRepository.save(anuncio);
    }

    public List<Anuncio> listarAnuncios() {
        return anuncioRepository.findAll();
    }

    public Anuncio obterAnuncioPorId(Long id) throws NotFoundException {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        if (anuncio.isPresent()) {
            return anuncio.get();
        } else {
            throw new NotFoundException("Anúncio não encontrado");
        }
    }

    @Transactional
    public void salvarSazonalidades(List<AnuncioSazonalidade> sazonalidades) {
        anuncioSazonalidadeRepository.saveAll(sazonalidades);
    }

    public void salvarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public Produto buscarProdutoPorDescricao(String descricao) {
        return produtoRepository.findByDescricao(descricao);
    }


    public Anuncio atualizarAnuncio(Long id, Anuncio anuncioAtualizado) throws NotFoundException {
        Anuncio anuncioExistente = obterAnuncioPorId(id);

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

    public void excluirSazonalidadesDoAnuncio(Anuncio anuncioExistente) {
        anuncioSazonalidadeRepository.delete(anuncioExistente);
    }
}
