package com.tcs_senac.ruralfacil.dto;

import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Cliente;
import com.tcs_senac.ruralfacil.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteDto {

    private long id;
    private AcessoPessoa acessoPessoa;
    private Endereco endereco;
    private String cpf;
    private String nome;
    private LocalDateTime dataNascimento;
    private String email;
    private String whatsApp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public AcessoPessoa getAcessoPessoa() {
        return acessoPessoa;
    }

    public void setAcessoPessoa(AcessoPessoa acessoPessoa) {
        this.acessoPessoa = acessoPessoa;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
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

    public ClienteDto() {
    }


    public static ClienteDto fromEntity(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setCpf(cliente.getCpf());
        dto.setNome(cliente.getNome());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setWhatsApp(cliente.getWhatsApp());
        dto.setEndereco(cliente.getEndereco());
        dto.setAcessoPessoa(cliente.getAcessoPessoa());
        return dto;
    }

    public Cliente toEntity() {
        Cliente cliente = new Cliente(this.getAcessoPessoa(),this.getEndereco() ,this.getCpf(), this.getNome(), this.getDataNascimento(),  this.getWhatsApp());
        // Configure outros campos, se aplicável
        return cliente;
    }

    public static List<ClienteDto> fromEntity(List<Cliente> clientes) {
        return clientes.stream().map(ClienteDto::fromEntity).collect(Collectors.toList());
    }

    public static Page<ClienteDto> fromEntity(Page<Cliente> clientes, Pageable pageable) {
        List<ClienteDto> clientesDTO = clientes.stream().map(ClienteDto::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(clientesDTO, pageable, clientes.getTotalElements());
    }
}