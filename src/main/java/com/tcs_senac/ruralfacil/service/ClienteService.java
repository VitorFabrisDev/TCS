package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.exception.ValidationException;
import com.tcs_senac.ruralfacil.model.Cliente;
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

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        validarCliente(cliente);
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
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setWhatsApp(clienteAtualizado.getWhatsApp());
        clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());
        return clienteRepository.save(clienteExistente);

    }

    private void validarCliente(Cliente cliente) throws ValidationException {
        if (!EmailValidator.getInstance().isValid(cliente.getEmail())) {
            throw new ValidationException("Aviso: Digite um endereço de e-mail válido!");
        }

        if (!CpfValid.isValid(cliente.getCpf())) {
            throw new ValidationException("Aviso: Digite um CPF válido!");
        }

        if (clienteRepository.existsByCpfAndIdNot(cliente.getCpf(), cliente.getId())) {
            throw new ValidationException("Aviso: CPF já cadastrado!");
        }

        Optional<Cliente> clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteExistente.isPresent() && clienteExistente.get().getId() == cliente.getId()) {
            throw new ValidationException("Aviso: E-mail já cadastrado!");
        }
    }
}
