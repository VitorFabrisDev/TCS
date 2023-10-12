package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.repository.PessoaRepository;
import com.tcs_senac.ruralfacil.model.Pessoa;
import java.util.List;

public class PessoaService {

    private PessoaRepository pessoaRepository;
    public Pessoa cadastrarPessoa(Pessoa pessoa) {

        return pessoaRepository.cadastrarPessoa(pessoa);
    }

    public List<Pessoa> listarPessoas() {


        return null;
    }

    public Pessoa obterPessoaPorId(Long id) {

        return null;
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoa) {

        return pessoa;
    }
}
