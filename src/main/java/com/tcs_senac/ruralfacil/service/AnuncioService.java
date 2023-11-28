package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.exception.ValidationException;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.model.AnuncioSazonalidade;
import com.tcs_senac.ruralfacil.model.Produto;
import com.tcs_senac.ruralfacil.repository.AgricultorRepository;
import com.tcs_senac.ruralfacil.repository.AnuncioRepository;
import com.tcs_senac.ruralfacil.repository.AnuncioSazonalidadeRepository;
import com.tcs_senac.ruralfacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    private final AnuncioSazonalidadeRepository anuncioSazonalidadeRepository;

    private final AgricultorRepository agricultorRepository;

    private final ProdutoRepository produtoRepository;

    @Autowired
    public AnuncioService(AnuncioRepository anuncioRepository, AnuncioSazonalidadeRepository anuncioSazonalidadeRepository, ProdutoRepository produtoRepository, AgricultorRepository agricultorRepository) {
        this.anuncioRepository = anuncioRepository;
        this.anuncioSazonalidadeRepository = anuncioSazonalidadeRepository;
        this.produtoRepository = produtoRepository;
        this.agricultorRepository = agricultorRepository;
    }

    public Anuncio cadastrarAnuncio(Anuncio anuncio) {
        try {
            return anuncioRepository.save(anuncio);
        } catch (DataIntegrityViolationException ex) {
            String mensagemErro = "Não foi possível salvar o anúncio. só pode existir um anúncio com o mesmo produto e agricultor para pordutos orgânicos e não orgânicos.";

            throw new ValidationException(mensagemErro, ex);
        }
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


    public void salvarSazonalidades(List<AnuncioSazonalidade> sazonalidades) {
        anuncioSazonalidadeRepository.saveAll(sazonalidades);
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);

    }

    public Produto buscarProdutoPorDescricao(String nomeProduto) {
        return produtoRepository.findByNomeProduto(nomeProduto);
    }

    public Agricultor buscarAgricultorPorId(Long id) {
        Optional<Agricultor> agricultor = agricultorRepository.findById(id);
      if(agricultor.isPresent()){
          return agricultor.get();
      }else{
          throw new NotFoundException("Agricultor não encontrado");
      }
    }


    public Anuncio atualizarAnuncio(Long id, Anuncio anuncioAtualizado) throws NotFoundException {
        Anuncio anuncioExistente = obterAnuncioPorId(id);

        anuncioExistente.setDescricao(anuncioAtualizado.getDescricao());
        anuncioExistente.setCategoria(anuncioAtualizado.getCategoria());
        anuncioExistente.setOrganico(anuncioAtualizado.getOrganico());
        anuncioExistente.setClassificacao(anuncioAtualizado.getClassificacao());
        anuncioExistente.setValor(anuncioAtualizado.getValor());
        anuncioExistente.setFoto1(anuncioAtualizado.getFoto1());
        anuncioExistente.setFoto2(anuncioAtualizado.getFoto2());
        anuncioExistente.setFoto3(anuncioAtualizado.getFoto3());
        anuncioExistente.setFoto4(anuncioAtualizado.getFoto4());
        anuncioExistente.setFoto5(anuncioAtualizado.getFoto5());

        try {
            return anuncioRepository.save(anuncioExistente);
        } catch (DataIntegrityViolationException ex) {
            String mensagemErro = "Não foi possível salvar o anúncio. Só pode existir um anúncio com o mesmo produto e agricultor para pordutos orgânicos e não orgânicos.";

            throw new ValidationException(mensagemErro, ex);
        }
    }

    public void excluirSazonalidadesDoAnuncio(Anuncio anuncioExistente) {

        List<AnuncioSazonalidade> sazonalidades = anuncioExistente.getAnunciosazonalidade();
        anuncioSazonalidadeRepository.deleteAllInBatch(sazonalidades);

    }
}
