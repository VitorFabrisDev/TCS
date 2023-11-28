package com.tcs_senac.ruralfacil.controller;

import com.tcs_senac.ruralfacil.dto.AnuncioDto;
import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Anuncio;
import com.tcs_senac.ruralfacil.model.AnuncioSazonalidade;
import com.tcs_senac.ruralfacil.model.Enum.Sazonalidade;
import com.tcs_senac.ruralfacil.model.Produto;
import com.tcs_senac.ruralfacil.service.AnuncioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Anuncio API", tags = { "Anuncio" })
@RestController
@RequestMapping("/api/anuncio")
public class AnuncioController extends AbstractController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    public AnuncioDto cadastrarAnuncio(@Valid @RequestBody AnuncioDto anuncioDto) {

        Anuncio anuncio = anuncioDto.toEntity();

        Produto produto = anuncioDto.getProduto();
        Produto produtoExistente = anuncioService.buscarProdutoPorDescricao(produto.getNomeProduto());

        if (produtoExistente == null) {
            produtoExistente = anuncioService.salvarProduto(produto);
        }

        anuncio.setProduto(produtoExistente);

        anuncioService.cadastrarAnuncio(anuncio);

        List<String> sazonalidades = anuncioDto.getSazonalidades();
        if (sazonalidades != null && !sazonalidades.isEmpty()) {
            List<AnuncioSazonalidade> anuncioSazonalidades = sazonalidades.stream()
                    .map(s -> new AnuncioSazonalidade(anuncio, Sazonalidade.valueOf(s)))
                    .collect(Collectors.toList());

            anuncioService.salvarSazonalidades(anuncioSazonalidades);
        }

        return AnuncioDto.fromEntity(anuncio);
    }

    @GetMapping
   // @PreAuthorize("hasRole('ADMIN')")
    public List<AnuncioDto> listarAnuncios() {
        List<Anuncio> anuncios = anuncioService.listarAnuncios();
        return anuncios.stream().map(AnuncioDto::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public AnuncioDto obterAnuncioPorId(@PathVariable Long id) throws NotFoundException {
        Anuncio anuncio = anuncioService.obterAnuncioPorId(id);
        return AnuncioDto.fromEntity(anuncio);
    }

    @PutMapping("/{id}")
   // @PreAuthorize("hasRole('ADMIN')")
    public AnuncioDto atualizarAnuncio(@PathVariable Long id, @RequestBody AnuncioDto anuncioDTO) throws NotFoundException {

        Anuncio anuncioExistente = anuncioService.obterAnuncioPorId(id);

        Anuncio anuncio = anuncioService.atualizarAnuncio(id, anuncioDTO.toEntity());

        anuncioService.excluirSazonalidadesDoAnuncio(anuncioExistente);

        List<String> sazonalidades = anuncioDTO.getSazonalidades();
        if (sazonalidades != null && !sazonalidades.isEmpty()) {
            List<AnuncioSazonalidade> anuncioSazonalidades = sazonalidades.stream()
                    .map(s -> new AnuncioSazonalidade(anuncioExistente, Sazonalidade.valueOf(s)))
                    .collect(Collectors.toList());

            anuncioService.salvarSazonalidades(anuncioSazonalidades);
        }

        return AnuncioDto.fromEntity(anuncio);
    }
}