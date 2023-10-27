package com.tcs_senac.ruralfacil.dto;

import com.tcs_senac.ruralfacil.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteDto {

    private String cpf;
    private String nome;
    private Date dataNascimento;
    private String email;
    private String whatsApp;

    // Adicione getters e setters

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

    public ClienteDto() {
    }

    public static ClienteDto fromEntity(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setCpf(cliente.getCpf());
        dto.setNome(cliente.getNome());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setEmail(cliente.getEmail());
        dto.setWhatsApp(cliente.getWhatsApp());
        return dto;
    }

    public Cliente toEntity() {
        Cliente cliente = new Cliente(this.getCpf(), this.getNome(), this.getDataNascimento(), this.getEmail(), this.getWhatsApp());
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