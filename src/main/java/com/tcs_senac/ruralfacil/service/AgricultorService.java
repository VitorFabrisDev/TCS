package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.AgricultorNotFoundException;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.model.Pessoa;
import com.tcs_senac.ruralfacil.repository.AgricultorRepository;
import com.tcs_senac.ruralfacil.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AgricultorService {
    private final AgricultorRepository agricultorRepository;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public AgricultorService(AgricultorRepository agricultorRepository, PessoaRepository pessoaRepository) {
        this.agricultorRepository = agricultorRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Agricultor cadastrarAgricultorPessoa(Agricultor agricultor) {

        Pessoa agro = pessoaRepository.save(agricultor.getPessoa());
        agricultor.setPessoa(agro);
        return agricultorRepository.save(agricultor);
    }


    public Pessoa obterAgricultorPessoa(Long id) throws AgricultorNotFoundException {
        Optional<Agricultor> agricultor = agricultorRepository.findById(id);
        if (agricultor.isPresent()) {
            return agricultor.get();
        } else {
            throw new AgricultorNotFoundException("Agricultor não encontrado");
        }
    }

    public Agricultor atualizarAgricultor(Long id, Agricultor agricultorAtualizado) throws AgricultorNotFoundException {
            Agricultor agricultorExistente = (Agricultor) obterAgricultorPessoa(id);
            agricultorExistente.setCpf(agricultorAtualizado.getCpf());
            agricultorExistente.setNome(agricultorAtualizado.getNome());
            agricultorExistente.setDataNascimento(agricultorAtualizado.getDataNascimento());
            agricultorExistente.setEmail(agricultorAtualizado.getEmail());
            agricultorExistente.setWhatsApp(agricultorAtualizado.getWhatsApp());
            agricultorExistente.setAtivo(agricultorAtualizado.getAtivo());
            agricultorExistente.setCaf(agricultorAtualizado.getCaf());
            agricultorExistente.setOrganico(agricultorAtualizado.getOrganico());
            return agricultorRepository.save(agricultorAtualizado);
    }
}
