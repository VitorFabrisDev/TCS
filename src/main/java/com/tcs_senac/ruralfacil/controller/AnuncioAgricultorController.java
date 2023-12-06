package com.tcs_senac.ruralfacil.controller;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.tcs_senac.ruralfacil.dto.AnuncioDto;
import com.tcs_senac.ruralfacil.model.*;
import com.tcs_senac.ruralfacil.model.Enum.Categoria;
import com.tcs_senac.ruralfacil.repository.AnuncioRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;


@Api(value = "Anuncio Agricultor API", tags = { "Anuncio Agricultor" })
@Controller
@RequestMapping("/api/AnuncioAgricultor")
public class AnuncioAgricultorController {

    @Autowired
    private AnuncioRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/html")
    public String listarAnuncios(
            @RequestParam(required = false) Long agricultor,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String organico,
            @RequestParam(required = false) String produto,
            @RequestParam(required = false) String valor,
            @RequestParam(required = false) String relevancia,
            Model model
    ) {
        List<AnuncioDto> anuncios = consultarAnuncios(agricultor, categoria, organico, produto, valor, relevancia);

        model.addAttribute("anuncios", anuncios);
        return "marketplace";
    }


    @GetMapping("/api")
    @ResponseBody
    public List<AnuncioDto> listarAnunciosApi(
            @RequestParam(required = false) Long agricultor,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String organico,
            @RequestParam(required = false) String produto,
            @RequestParam(required = false) String valor,
            @RequestParam(required = false) String relevancia
    ) {
        return consultarAnuncios(agricultor, categoria, organico, produto, valor, relevancia);
    }

    private List<AnuncioDto> consultarAnuncios(Long agricultor, String categoria, String organico,
                                               String produto, String valor, String relevancia) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);
        query.select(QAnuncio.anuncio, QProduto.produto, QAgricultor.agricultor, QAnuncioSazonalidade.anuncioSazonalidade)
                .from(QAnuncio.anuncio)
                .innerJoin(QAgricultor.agricultor).on(QAnuncio.anuncio.agricultor.eq(QAgricultor.agricultor))
                .innerJoin(QProduto.produto).on(QAnuncio.anuncio.produto.eq(QProduto.produto))
                .leftJoin(QAnuncioSazonalidade.anuncioSazonalidade).on(QAnuncio.anuncio.anunciosazonalidade.any().sazonalidade.eq(QAnuncioSazonalidade.anuncioSazonalidade.sazonalidade))
                .leftJoin(QAnuncioClienteClassificacao.anuncioClienteClassificacao).on(QAnuncio.anuncio.anuncioClienteClassificacoes.any().eq(QAnuncioClienteClassificacao.anuncioClienteClassificacao))
                .where(Expressions.booleanTemplate("1=1"));


        if (agricultor != null) {
            query.where(QAnuncio.anuncio.agricultor.id.eq(Long.valueOf(agricultor)));
        }

        if (categoria != null) {
            query.where(QAnuncio.anuncio.categoria.eq(Categoria.valueOf(categoria)));
        }


        if (organico != null) {
            Boolean organicoValue = Boolean.valueOf(organico);
            query.where(QAnuncio.anuncio.organico.eq(organicoValue));
        }


        if (produto != null) {
            orderSpecifiers.add(produto.equalsIgnoreCase("asc") ? QAnuncio.anuncio.produto.nomeProduto.asc() : QAnuncio.anuncio.produto.nomeProduto.desc());
        }

        if (valor != null) {
            orderSpecifiers.add(valor.equalsIgnoreCase("asc") ? QAnuncio.anuncio.valor.asc() : QAnuncio.anuncio.valor.desc());
        }

        if (relevancia != null) {
            orderSpecifiers.add(QAnuncio.anuncio.classificacao.desc());
        }

        if (!orderSpecifiers.isEmpty()) {
            query.orderBy(orderSpecifiers.toArray(new OrderSpecifier<?>[0]));

        }
            List<AnuncioDto> anunciosDto = query.fetch().stream()
                    .map(tuple -> {
                        AnuncioDto anuncioDto = AnuncioDto.fromEntity(tuple.get(QAnuncio.anuncio));
                        anuncioDto.setAgricultor(tuple.get(QAgricultor.agricultor));
                        anuncioDto.setProduto(tuple.get(QProduto.produto));
                        anuncioDto.setSazonalidades(tuple.get(QAnuncio.anuncio).getAnunciosazonalidade()
                                .stream()
                                .map(anuncioSazonalidade -> anuncioSazonalidade.getSazonalidade().name())
                                .collect(Collectors.toList()));

                        return anuncioDto;
                    })
                    .collect(Collectors.toMap(
                            AnuncioDto::getId,
                            anuncioDto -> anuncioDto,
                            (existing, replacement) -> existing,
                            LinkedHashMap::new
                    ))
                    .values()
                    .stream()
                    .collect(Collectors.toList());

            return anunciosDto;
        }
}
