package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.AgricultorNotFoundException;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.model.Pessoa;
import com.tcs_senac.ruralfacil.repository.AgricultorRepository;
import com.tcs_senac.ruralfacil.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    public List<Agricultor> listarAgricultores() {
        return agricultorRepository.findAll();
    }

    public Agricultor obterAgricultorPessoa(Long id) throws AgricultorNotFoundException {
        Optional<Agricultor> agricultor = agricultorRepository.findById(id);
        if (agricultor.isPresent()) {
            return agricultor.get();
        } else {
            throw new AgricultorNotFoundException("Agricultor não encontrado");
        }
    }

    public Agricultor atualizarAgricultor(Long id, Agricultor agricultorAtualizado) throws AgricultorNotFoundException {
            Agricultor agricultorExistente = (Agricultor) obterAgricultorPessoa(id);
            agricultorExistente.setPessoa(agricultorAtualizado.getPessoa());
            agricultorExistente.setInscricaoEstadual(agricultorAtualizado.getInscricaoEstadual());
            agricultorExistente.setCaf(agricultorAtualizado.getCaf());
            agricultorExistente.setOrganico(agricultorAtualizado.getOrganico());
            agricultorExistente.setAtivo(agricultorAtualizado.getAtivo());
            return agricultorRepository.save(agricultorExistente);
    }


}
