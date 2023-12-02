package com.tcs_senac.ruralfacil.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class AgricultorDto {

    private long id;
    private AcessoPessoa acessoPessoa;
    private Endereco endereco;
    private String cpf;
    private String nome;
    private LocalDateTime dataNascimento;
    private String whatsApp;
    private String inscricaoEstadual;
    private String caf;
    private boolean organico;
    private boolean ativo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isOrganico() {
        return organico;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public AcessoPessoa getAcessoPessoa() {
        return acessoPessoa;
    }

    public void setAcessoPessoa(AcessoPessoa acessoPessoa) {
        this.acessoPessoa = acessoPessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCaf() {
        return caf;
    }

    public void setCaf(String caf) {
        this.caf = caf;
    }

    public boolean getOrganico() {
        return organico;
    }

    public void setOrganico(boolean organico) {
        this.organico = organico;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public AgricultorDto() {
    }

    public static AgricultorDto fromEntity(Agricultor agricultor) {
        AgricultorDto dto = new AgricultorDto();
        dto.setId(agricultor.getId());
        dto.setCpf(agricultor.getCpf());
        dto.setNome(agricultor.getNome());
        dto.setDataNascimento(agricultor.getDataNascimento());
        dto.setWhatsApp(agricultor.getWhatsApp());
        dto.setInscricaoEstadual(agricultor.getInscricaoEstadual());
        dto.setCaf(agricultor.getCaf());
        dto.setOrganico(agricultor.getOrganico());
        dto.setAtivo(agricultor.getAtivo());
        dto.setEndereco(agricultor.getEndereco());
        dto.setAcessoPessoa(agricultor.getAcessoPessoa());
        return dto;
    }

    public Agricultor toEntity() {
        Agricultor agricultor = new Agricultor(this.getAcessoPessoa(), this.getEndereco(),this.getCpf(), this.getNome(), this.getDataNascimento(), this.getWhatsApp(), this.getInscricaoEstadual(), this.getCaf(), this.getOrganico(), this.getAtivo());
        agricultor.setId(this.getId());
        agricultor.setInscricaoEstadual(this.getInscricaoEstadual());
        agricultor.setCaf(this.getCaf());
        agricultor.setOrganico(this.getOrganico());
        agricultor.setAtivo(this.getAtivo());

        return agricultor;
    }

    public static List<AgricultorDto> fromEntity(List<Agricultor> agricultores) {
        return agricultores.stream().map(AgricultorDto::fromEntity).collect(Collectors.toList());
    }

    public static Page<AgricultorDto> fromEntity(Page<Agricultor> agricultores, Pageable pageable) {
        List<AgricultorDto> agricultoresDTO = agricultores.stream().map(AgricultorDto::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(agricultoresDTO, pageable, agricultores.getTotalElements());
    }

}
