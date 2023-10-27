package com.tcs_senac.ruralfacil.config.security.services;

import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.repository.AcessoPessoaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AcessoPessoaRepository acessoPessoaRepository;

    public UserDetailsServiceImpl(AcessoPessoaRepository acessoPessoaRepository) {
        this.acessoPessoaRepository = acessoPessoaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<AcessoPessoa> acessoPessoa = acessoPessoaRepository.findByLogin(login);
        if (acessoPessoa == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return UserDetailsImpl.build(acessoPessoa);
    }
}