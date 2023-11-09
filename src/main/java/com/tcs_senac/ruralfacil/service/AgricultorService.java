package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.repository.AgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgricultorService {
    private final AgricultorRepository agricultorRepository;

    @Autowired
    public AgricultorService(AgricultorRepository agricultorRepository) {
        this.agricultorRepository = agricultorRepository;
    }

    public Agricultor cadastrarAgricultor(Agricultor agricultor) {
        return agricultorRepository.save(agricultor);

    }

    public List<Agricultor> listarAgricultores() {
        return agricultorRepository.findAll();
    }

    public Agricultor obterAgricultorPorId(Long id) throws NotFoundException {
        Optional<Agricultor> agricultor = agricultorRepository.findById(id);
        if (agricultor.isPresent()) {
            return agricultor.get();
        } else {
            throw new NotFoundException("Agricultor não encontrado");
        }
    }

    public Agricultor atualizarAgricultor(Long id, Agricultor agricultorAtualizado) throws NotFoundException {
            Agricultor agricultorExistente = (Agricultor) obterAgricultorPorId(id);
            agricultorExistente.setInscricaoEstadual(agricultorAtualizado.getInscricaoEstadual());
            agricultorExistente.setCaf(agricultorAtualizado.getCaf());
            agricultorExistente.setOrganico(agricultorAtualizado.getOrganico());
            agricultorExistente.setAtivo(agricultorAtualizado.getAtivo());
            return agricultorRepository.save(agricultorExistente);
    }


}
