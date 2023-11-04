package com.tcs_senac.ruralfacil.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Agricultor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class AgricultorDto {

    private AcessoPessoa acessoPessoa;
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private String email;
    private String whatsApp;
    private String inscricaoEstadual;
    private String caf;
    private char organico;
    private char ativo;

    public AcessoPessoa getAcessoPessoa() {
        return acessoPessoa;
    }

    public void setAcessoPessoa(AcessoPessoa acessoPessoa) {
        this.acessoPessoa = acessoPessoa;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public char getOrganico() {
        return organico;
    }

    public void setOrganico(char organico) {
        this.organico = organico;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

    public AgricultorDto() {
    }

    public static AgricultorDto fromEntity(Agricultor agricultor) {
        AgricultorDto dto = new AgricultorDto();
        dto.setCpf(agricultor.getCpf());
        dto.setNome(agricultor.getNome());
        dto.setDataNascimento(agricultor.getDataNascimento());
        dto.setEmail(agricultor.getEmail());
        dto.setWhatsApp(agricultor.getWhatsApp());
        dto.setInscricaoEstadual(agricultor.getInscricaoEstadual());
        dto.setCaf(agricultor.getCaf());
        dto.setOrganico(agricultor.getOrganico());
        dto.setAtivo(agricultor.getAtivo());
        return dto;
    }

    public Agricultor toEntity() {
        Agricultor agricultor = new Agricultor(this.getAcessoPessoa(), this.getCpf(), this.getNome(), this.getDataNascimento(), this.getEmail(), this.getWhatsApp());
        agricultor.setInscricaoEstadual(this.getInscricaoEstadual());
        agricultor.setCaf(this.getCaf());
        agricultor.setOrganico(this.getOrganico());
        agricultor.setAtivo(this.getAtivo());
        // Configure outros campos, se aplicável
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
