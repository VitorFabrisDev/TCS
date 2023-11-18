package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.exception.ValidationException;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.repository.AgricultorRepository;
import com.tcs_senac.ruralfacil.util.CpfValid;
import com.tcs_senac.ruralfacil.util.InscricaoEstadualValid;
import org.apache.commons.validator.routines.EmailValidator;
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
        validarAgricultor(agricultor);
        validarAgricultorExistente(agricultor);

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

        validarAgricultor(agricultorAtualizado);
        Agricultor agricultorExistente = (Agricultor) obterAgricultorPorId(id);
        agricultorExistente.setInscricaoEstadual(agricultorAtualizado.getInscricaoEstadual());
        agricultorExistente.setCaf(agricultorAtualizado.getCaf());
        agricultorExistente.setOrganico(agricultorAtualizado.getOrganico());
        agricultorExistente.setAtivo(agricultorAtualizado.getAtivo());
        return agricultorRepository.save(agricultorExistente);
    }

    private void validarAgricultor(Agricultor agricultor) throws ValidationException {
        if (!EmailValidator.getInstance().isValid(agricultor.getEmail())) {
            throw new ValidationException("Aviso: Digite um endereço de e-mail válido!");
        }

        if (!CpfValid.isValid(agricultor.getCpf())) {
            throw new ValidationException("Aviso: Digite uma CPF válido!");
        }

        if (!InscricaoEstadualValid.validaIESantaCatarina(agricultor.getInscricaoEstadual())) {
            throw new ValidationException("Aviso: Digite uma IE válida");
        }


        Optional<Agricultor> agricultorExistente = agricultorRepository.findByEmail(agricultor.getEmail());
        if (agricultorExistente.isPresent() && agricultorExistente.get().getId() == agricultor.getId()) {
            throw new ValidationException("Aviso: E-mail já cadastrado!");
        }
    }

    private void validarAgricultorExistente(Agricultor agricultor) throws ValidationException {
        if (agricultorRepository.existsByCpfAndIdNot(agricultor.getCpf(), agricultor.getId())) {
            throw new ValidationException("Aviso: CPF já cadastrado!");
        }
    }


}
