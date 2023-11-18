package com.tcs_senac.ruralfacil.controller;

import com.tcs_senac.ruralfacil.dto.ClienteDto;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Cliente;
import com.tcs_senac.ruralfacil.model.Endereco;
import com.tcs_senac.ruralfacil.service.AcessoPessoaService;
import com.tcs_senac.ruralfacil.service.ClienteService;
import com.tcs_senac.ruralfacil.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController extends AbstractController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private AcessoPessoaService acessoPessoaService;

    @PostMapping
    public Cliente cadastrarCliente(@Valid @RequestBody ClienteDto clienteDto) {

        AcessoPessoa acessoPessoa = acessoPessoaService.obterAcessoPessoaByLogin(clienteDto.getAcessoPessoa().getLogin());

        Cliente cliente = mapClienteDTOToEntity(clienteDto);



        Endereco endereco = cliente.getEndereco();
        if (endereco != null) {
            enderecoService.cadastrarEndereco(endereco);
        }

        cliente.setAcessoPessoa(acessoPessoa);

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
        Cliente cliente = mapClienteDTOToEntity(clienteDto);
        Endereco endereco = cliente.getEndereco();

        Cliente clienteExistente = clienteService.obterClientePorId(id);

        if (endereco != null) {
            enderecoService.atualizarEndereco(clienteExistente.getEndereco().getId(),endereco);
        }
        AcessoPessoa acessoPessoa = cliente.getAcessoPessoa();
        if(acessoPessoa != null){
            acessoPessoaService.atualizarAcessoPessoa(clienteExistente.getAcessoPessoa().getId(),acessoPessoa);
        }

        return clienteService.atualizarCliente(id, cliente);
    }

    private Cliente mapClienteDTOToEntity(ClienteDto clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getAcessoPessoa(), clienteDTO.getEndereco(), clienteDTO.getCpf(), clienteDTO.getNome(), clienteDTO.getDataNascimento(), clienteDTO.getEmail(), clienteDTO.getWhatsApp());
        return cliente;
    }
}