package com.tcs_senac.ruralfacil.service;

import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.model.Endereco;
import com.tcs_senac.ruralfacil.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco cadastrarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);

    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco obterEnderecoPorId(Long id) throws NotFoundException {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()) {
            return endereco.get();
        } else {
            throw new NotFoundException("Endereço não encontrado");
        }

    }

    public Endereco atualizarEndereco(Long id, Endereco enderecoAtualizado) throws NotFoundException {
        Endereco enderecoExistente = obterEnderecoPorId(id);
        enderecoExistente.setLogradouro(enderecoAtualizado.getLogradouro());
        enderecoExistente.setBairro(enderecoAtualizado.getBairro());
        enderecoExistente.setNumero(enderecoAtualizado.getNumero());
        enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
        enderecoExistente.setCep(enderecoAtualizado.getCep());
        enderecoExistente.setMunicipio(enderecoAtualizado.getMunicipio());
        enderecoExistente.setInscricaoIncra(enderecoAtualizado.getInscricaoIncra());
        return enderecoRepository.save(enderecoExistente);
    }
}
