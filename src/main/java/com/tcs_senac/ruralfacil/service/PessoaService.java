package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.PessoaNotFoundException;
import com.tcs_senac.ruralfacil.repository.PessoaRepository;
import com.tcs_senac.ruralfacil.model.Pessoa;
import java.util.List;
import java.util.Optional;

public class PessoaService {

    private PessoaRepository pessoaRepository;
    public Pessoa cadastrarPessoa(Pessoa pessoa) {

        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarPessoas() {

        return pessoaRepository.findAll();
    }

    public Pessoa obterPessoaPorId(Long id) throws PessoaNotFoundException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            return pessoa.get();
        } else {
            throw new PessoaNotFoundException("Pessoa não encontrada");
        }
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) throws PessoaNotFoundException {
        Pessoa pessoaExistente = obterPessoaPorId(id);
        pessoaExistente.setCpf(pessoaAtualizada.getCpf());
        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setEmail(pessoaAtualizada.getEmail());
        pessoaExistente.setWhatsApp(pessoaAtualizada.getWhatsApp());
        return pessoaRepository.save(pessoaExistente);
    }
}
