package com.tcs_senac.ruralfacil.controller;

import com.tcs_senac.ruralfacil.dto.ClienteDto;
import com.tcs_senac.ruralfacil.model.Cliente;
import com.tcs_senac.ruralfacil.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController extends AbstractController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente cadastrarCliente(@Valid @RequestBody ClienteDto clienteDto) {
        // Mapeie o ClienteDTO para a entidade Cliente antes de chamar o serviço
        Cliente cliente = mapClienteDTOToEntity(clienteDto);
        return clienteService.cadastrarCliente(cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public Cliente obterClientePorId(@PathVariable Long id) {
        return clienteService.obterClientePorId(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        // Mapeie o ClienteDTO para a entidade Cliente antes de chamar o serviço
        Cliente cliente = mapClienteDTOToEntity(clienteDto);
        return clienteService.atualizarCliente(id, cliente);
    }

    // Método para mapear um ClienteDTO para a entidade Cliente
    private Cliente mapClienteDTOToEntity(ClienteDto clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getCpf(), clienteDTO.getNome(), clienteDTO.getDataNascimento(), clienteDTO.getEmail(), clienteDTO.getWhatsApp());
        // Outros campos, se aplicável
        return cliente;
    }
}