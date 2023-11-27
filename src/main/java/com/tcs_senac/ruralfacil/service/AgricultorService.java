package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.exception.ValidationException;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.repository.AcessoPessoaRepository;
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
    private final AcessoPessoaRepository acessoPessoaRepository;

    @Autowired
    public AgricultorService(AgricultorRepository agricultorRepository, AcessoPessoaRepository acessoPessoaRepository) {

        this.agricultorRepository = agricultorRepository;
        this.acessoPessoaRepository = acessoPessoaRepository;
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

        if (!CpfValid.isValid(agricultor.getCpf())) {
            throw new ValidationException("Aviso: Digite uma CPF válido!");
        }

        if (!InscricaoEstadualValid.validaIESantaCatarina(agricultor.getInscricaoEstadual())) {
            throw new ValidationException("Aviso: Digite uma IE válida");
        }


    }

    private void validarAgricultorExistente(Agricultor agricultor) throws ValidationException {
        if (agricultorRepository.existsByCpfAndIdNot(agricultor.getCpf(), agricultor.getId())) {
            throw new ValidationException("Aviso: CPF já cadastrado!");
        }
    }


    public Agricultor obterAgricultorPorAcessoPessoa(Long idAcessoPessoa) throws NotFoundException {
        Optional<AcessoPessoa> acessoPessoa = acessoPessoaRepository.findById(idAcessoPessoa);

        Optional<Agricultor> agricultor = agricultorRepository.findAgricultorByAcessoPessoa(acessoPessoa);
        if (agricultor.isPresent()) {
            return agricultor.get();
        } else {
            throw new NotFoundException("Agricultor não encontrado");
        }
    }
}
