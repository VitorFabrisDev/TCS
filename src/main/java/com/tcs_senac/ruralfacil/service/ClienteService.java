package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.exception.ValidationException;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Cliente;
import com.tcs_senac.ruralfacil.repository.AcessoPessoaRepository;
import com.tcs_senac.ruralfacil.repository.ClienteRepository;
import com.tcs_senac.ruralfacil.util.CpfValid;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final AcessoPessoaRepository acessoPessoaRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, AcessoPessoaRepository acessoPessoaRepository) {

        this.clienteRepository = clienteRepository;
        this.acessoPessoaRepository = acessoPessoaRepository;
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        validarCliente(cliente);
        validarCpfExiste(cliente);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obterClientePorId(Long id) throws NotFoundException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new NotFoundException("Cliente não encontrado");
        }
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) throws NotFoundException {
        validarCliente(clienteAtualizado);
        Cliente clienteExistente = obterClientePorId(id);
        clienteExistente.setCpf(clienteAtualizado.getCpf());
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setWhatsApp(clienteAtualizado.getWhatsApp());
        clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());
        return clienteRepository.save(clienteExistente);

    }

    private void validarCliente(Cliente cliente) throws ValidationException {

        if (!CpfValid.isValid(cliente.getCpf())) {
            throw new ValidationException("Aviso: Digite um CPF válido!");
        }

    }
    private void validarCpfExiste(Cliente cliente) throws ValidationException {
        if (clienteRepository.existsByCpfAndIdNot(cliente.getCpf(), cliente.getId())) {
            throw new ValidationException("Aviso: CPF já cadastrado!");
        }
    }



    public Cliente obterClientePorAcessoPessoa(Long idacessopessoa) {
        Optional<AcessoPessoa> acessoPessoa = acessoPessoaRepository.findById(idacessopessoa);
        Optional<Cliente> cliente = clienteRepository.findClienteByAcessoPessoa(acessoPessoa);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new NotFoundException("Cliente não encontrado");
        }
    }
}
