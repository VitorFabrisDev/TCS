package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.repository.AcessoPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcessoPessoaService {

    @Autowired
    PasswordEncoder encoder;

    private final AcessoPessoaRepository acessoPessoaRepository;

    @Autowired
    public AcessoPessoaService(AcessoPessoaRepository acessoPessoaRepository) {
        this.acessoPessoaRepository = acessoPessoaRepository;
    }

    public List<AcessoPessoa> listarAcessos() {
        return this.acessoPessoaRepository.findAll();
    }

    public AcessoPessoa cadastrarAcessoPessoa(AcessoPessoa acessoPessoa) {

        return acessoPessoaRepository.save(acessoPessoa);
    }

    public AcessoPessoa obterAcessoPessoaPorId(Long id) throws NotFoundException {
        Optional<AcessoPessoa> acessoPessoa = acessoPessoaRepository.findById(id);
        if (acessoPessoa.isPresent())
            return acessoPessoa.get();
        else {
            throw new NotFoundException("Login ou senha não encontrada");
        }
    }

    public AcessoPessoa obterAcessoPessoaByLogin(String login) {

        Optional<AcessoPessoa> acessoPessoa = acessoPessoaRepository.findByLogin(login);
        //return acessoPessoaRepository.findByLogin(login);
        if (acessoPessoa.isPresent())
            return acessoPessoa.get();
        else {
            throw new NotFoundException("E-mail não cadastrado no Login");
        }
    }

    public AcessoPessoa atualizarAcessoPessoa(Long id, AcessoPessoa acessoPessoaAtualizada) throws NotFoundException {
        AcessoPessoa acessoExistente = (AcessoPessoa) obterAcessoPessoaPorId(id);
        acessoExistente.setLogin(acessoPessoaAtualizada.getLogin());
        if (acessoPessoaAtualizada.getPassword() != null && !acessoPessoaAtualizada.getPassword().isEmpty()) {
            acessoExistente.setPassword(encoder.encode(acessoPessoaAtualizada.getPassword()));
        }
        if (acessoPessoaAtualizada.getQtdAcesso() != 0) {
            acessoExistente.setQtdAcesso(acessoPessoaAtualizada.getQtdAcesso());
        }
        if (acessoPessoaAtualizada.getDtUltAcesso() != null) {
            acessoExistente.setDtUltAcesso(acessoPessoaAtualizada.getDtUltAcesso());
        }
        return acessoPessoaRepository.save(acessoExistente);
    }

}
