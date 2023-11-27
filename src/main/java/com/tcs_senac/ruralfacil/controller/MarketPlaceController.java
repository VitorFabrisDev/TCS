package com.tcs_senac.ruralfacil.controller;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.tcs_senac.ruralfacil.dto.AnuncioDto;
import com.tcs_senac.ruralfacil.model.*;
import com.tcs_senac.ruralfacil.model.Enum.Categoria;
import com.tcs_senac.ruralfacil.model.Enum.Sazonalidade;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;


@Api(value = "MarketPlace API", tags = { "MarketPlace" })
@Controller
@RequestMapping("/api/MarketPlace")
public class MarketPlaceController {

    @Autowired
    private AnuncioRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/html")
    public String listarAnuncios(
            @RequestParam(required = false) String agricultor,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String sazonalidade,
            @RequestParam(required = false) String organico,
            @RequestParam(required = false) String produto,
            @RequestParam(required = false) String produtofilter,
            @RequestParam(required = false) String valor,
            @RequestParam(required = false) String relevancia,
            Model model
    ) {
        List<AnuncioDto> anuncios = consultarAnuncios(agricultor, categoria, sazonalidade, organico, produto, produtofilter, valor, relevancia);

        model.addAttribute("anuncios", anuncios);
        return "marketplace";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<AnuncioDto> listarAnunciosApi(
            @RequestParam(required = false) String agricultor,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String sazonalidade,
            @RequestParam(required = false) String organico,
            @RequestParam(required = false) String produto,
            @RequestParam(required = false) String produtofilter,
            @RequestParam(required = false) String valor,
            @RequestParam(required = false) String relevancia
    ) {
        return consultarAnuncios(agricultor, categoria, sazonalidade, organico, produto, produtofilter, valor, relevancia);
    }

    private List<AnuncioDto> consultarAnuncios(String agricultor, String categoria, String sazonalidade, String organico,
                                            String produto, String produtofilter, String valor, String relevancia) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);
        query.select(QAnuncio.anuncio, QProduto.produto, QAgricultor.agricultor, QAnuncioSazonalidade.anuncioSazonalidade.sazonalidade)
                .from(QAnuncio.anuncio)
                .innerJoin(QAgricultor.agricultor).on(QAnuncio.anuncio.agricultor.eq(QAgricultor.agricultor))
                .innerJoin(QProduto.produto).on(QAnuncio.anuncio.produto.eq(QProduto.produto))
                .innerJoin(QAnuncioSazonalidade.anuncioSazonalidade).on(QAnuncio.anuncio.anunciosazonalidade.any().eq(QAnuncioSazonalidade.anuncioSazonalidade))
                .leftJoin(QAnuncioClienteClassificacao.anuncioClienteClassificacao).on(QAnuncio.anuncio.anuncioClienteClassificacoes.any().eq(QAnuncioClienteClassificacao.anuncioClienteClassificacao))
                .where(Expressions.booleanTemplate("1=1"));


        if (agricultor == null && categoria == null && sazonalidade == null && produtofilter == null &&
                organico == null && produto == null && valor == null && relevancia == null) {

            String mesAtual = obterMesAtualEmPortugues();

            ComparableExpressionBase<Sazonalidade> sazonalidadePath = QAnuncioSazonalidade.anuncioSazonalidade.sazonalidade;
            NumberPath<Double> classificacaoPath = QAnuncioClienteClassificacao.anuncioClienteClassificacao.classificacao;
            NumberPath<Long> qtdInteracaoPath = QAnuncioClienteClassificacao.anuncioClienteClassificacao.qtdInteracao;
            NumberPath<Long> qtdAcessoPath =  QAnuncioClienteClassificacao.anuncioClienteClassificacao.qtdAcesso;
            NumberPath<Double> clasAnuncio = QAnuncio.anuncio.classificacao;

            query.orderBy(
                    sazonalidadePath.when(Sazonalidade.valueOf(mesAtual)).then(0).otherwise(1).desc(),
                    classificacaoPath.desc(),
                    qtdInteracaoPath.desc(),
                    qtdAcessoPath.desc(),
                    clasAnuncio.desc()
            );

        } else {
            if (agricultor != null) {
                query.where(QAnuncio.anuncio.agricultor.nome.contains(agricultor));
            }

            if (categoria != null) {
                query.where(QAnuncio.anuncio.categoria.eq(Categoria.valueOf(categoria)));
            }

            if (sazonalidade != null) {
                query.where(QAnuncio.anuncio.anunciosazonalidade.any().sazonalidade.eq(Sazonalidade.valueOf(sazonalidade)));
            }

            if (organico != null) {
                Boolean organicoValue = Boolean.valueOf(organico);
                query.where(QAnuncio.anuncio.organico.eq(organicoValue));
            }

            if (produtofilter != null) {
                query.where(QAnuncio.anuncio.produto.descricao.contains(produtofilter));
            }

            if (produto != null) {
                orderSpecifiers.add(produto.equalsIgnoreCase("asc") ? QAnuncio.anuncio.produto.descricao.asc() : QAnuncio.anuncio.produto.descricao.desc());
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
        }

        List<AnuncioDto> anunciosDto = query.fetch().stream()
                .map(tuple -> {
                    AnuncioDto anuncioDto = AnuncioDto.fromEntity(tuple.get(QAnuncio.anuncio));
                    anuncioDto.setAgricultor(tuple.get(QAgricultor.agricultor));
                    anuncioDto.setProduto(tuple.get(QProduto.produto));
                    anuncioDto.setSazonalidades(Collections.singletonList(tuple.get(QAnuncioSazonalidade.anuncioSazonalidade.sazonalidade).name()));
                    return anuncioDto;
                })
                .collect(Collectors.toMap(
                        AnuncioDto::getId, // Chave do mapa: supondo que você tenha um método getId() em AnuncioDto
                        anuncioDto -> anuncioDto, // Valor do mapa: o próprio objeto AnuncioDto
                        (existing, replacement) -> existing, // Se houver chaves duplicadas, mantenha a primeira ocorrência
                        LinkedHashMap::new // Usar um LinkedHashMap para manter a ordem de inserção
                ))
                .values() // Pegar apenas os valores do mapa, que agora são AnuncioDto únicos
                .stream()
                .collect(Collectors.toList());

        return anunciosDto;
    }

    private String obterMesAtualEmPortugues() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR"));
        return LocalDate.now().format(formatter).toUpperCase();
    }
}
