package com.erpoticastec.backenderp.service;

import com.erpoticastec.backenderp.dto.PessoaRequestDTO;
import com.erpoticastec.backenderp.model.ClienteFornecedor;
import com.erpoticastec.backenderp.model.TipoPessoa;
import com.erpoticastec.backenderp.repository.ClienteFornecedorRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import com.erpoticastec.backenderp.repository.TipoPessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteFornecedorService {

    @Autowired
    ClienteFornecedorRepository clienteFornecedorRepository;

    @Autowired
    TipoPessoaRepository tipoPessoaRepository;

    public void cadastrarClienteFornecedor(PessoaRequestDTO pessoaRequestDTO) {
        ClienteFornecedor clienteFornecedor = new ClienteFornecedor();
        clienteFornecedor.setNome(pessoaRequestDTO.nome());
        clienteFornecedor.setEmail(pessoaRequestDTO.email());
        clienteFornecedor.setDocumento(pessoaRequestDTO.documento());

        TipoPessoa tipoPessoa = tipoPessoaRepository.findById(pessoaRequestDTO.tipoPessoaId())
                .orElseThrow(() -> new RuntimeException("Tipo de pessoa não encontrado"));

        clienteFornecedor.setTipoPessoa(tipoPessoa);
        clienteFornecedor.setTelefone(pessoaRequestDTO.telefone());
        clienteFornecedor.setEnderecoLogradouro(pessoaRequestDTO.enderecoLogradouro());
        clienteFornecedor.setEnderecoNumero(pessoaRequestDTO.enderecoNumero());
        clienteFornecedor.setEnderecoComplemento(pessoaRequestDTO.enderecoComplemento());
        clienteFornecedor.setEnderecoBairro(pessoaRequestDTO.enderecoBairro());
        clienteFornecedor.setEnderecoCidade(pessoaRequestDTO.enderecoCidade());
        clienteFornecedor.setEnderecoEstado(pessoaRequestDTO.enderecoEstado());
        clienteFornecedor.setEnderecoCep(pessoaRequestDTO.enderecoCep());
        clienteFornecedor.setDataCadastro(LocalDateTime.now());
        clienteFornecedor.setAtivo(pessoaRequestDTO.ativo());
        clienteFornecedor.setObservacoes(pessoaRequestDTO.observacoes());

        clienteFornecedorRepository.save(clienteFornecedor);
    }

    public void updateClienteFornecedor(PessoaRequestDTO pessoaUpdateDTO) {
        ClienteFornecedor existingClienteFornecedor = clienteFornecedorRepository.findById(pessoaUpdateDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente/Fornecedor não encontrado"));

        if (pessoaUpdateDTO.nome() != null) {
            existingClienteFornecedor.setNome(pessoaUpdateDTO.nome());
        }

        if (pessoaUpdateDTO.email() != null) {
            existingClienteFornecedor.setEmail(pessoaUpdateDTO.email());
        }

        if (pessoaUpdateDTO.telefone() != null) {
            existingClienteFornecedor.setTelefone(pessoaUpdateDTO.telefone());
        }

        if (pessoaUpdateDTO.enderecoLogradouro() != null) {
            existingClienteFornecedor.setEnderecoLogradouro(pessoaUpdateDTO.enderecoLogradouro());
        }

        if (pessoaUpdateDTO.enderecoNumero() != null) {
            existingClienteFornecedor.setEnderecoNumero(pessoaUpdateDTO.enderecoNumero());
        }

        if (pessoaUpdateDTO.enderecoComplemento() != null) {
            existingClienteFornecedor.setEnderecoComplemento(pessoaUpdateDTO.enderecoComplemento());
        }

        if (pessoaUpdateDTO.enderecoBairro() != null) {
            existingClienteFornecedor.setEnderecoBairro(pessoaUpdateDTO.enderecoBairro());
        }

        if (pessoaUpdateDTO.enderecoCidade() != null) {
            existingClienteFornecedor.setEnderecoCidade(pessoaUpdateDTO.enderecoCidade());
        }

        if (pessoaUpdateDTO.enderecoEstado() != null) {
            existingClienteFornecedor.setEnderecoEstado(pessoaUpdateDTO.enderecoEstado());
        }

        if (pessoaUpdateDTO.enderecoCep() != null) {
            existingClienteFornecedor.setEnderecoCep(pessoaUpdateDTO.enderecoCep());
        }

        if (pessoaUpdateDTO.ativo() != null) {
            existingClienteFornecedor.setAtivo(pessoaUpdateDTO.ativo());
        }

        if (pessoaUpdateDTO.observacoes() != null) {
            existingClienteFornecedor.setObservacoes(pessoaUpdateDTO.observacoes());
        }

        if (pessoaUpdateDTO.tipoPessoaId() != null) {
            TipoPessoa tipoPessoa = tipoPessoaRepository.findById(pessoaUpdateDTO.tipoPessoaId())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de Pessoa não encontrado"));
            existingClienteFornecedor.setTipoPessoa(tipoPessoa);
        }

        clienteFornecedorRepository.save(existingClienteFornecedor);
    }

    public List<ClienteFornecedor> buscarClientes(String documento, String nome, String email) {
        if (documento != null) {
            return clienteFornecedorRepository.findByDocumento(documento)
                    .map(List::of)
                    .orElse(Collections.emptyList());
        }

        if (email != null) {
            return clienteFornecedorRepository.findByEmail(email)
                    .map(List::of)
                    .orElse(Collections.emptyList());
        }

        if (nome != null) {
            return clienteFornecedorRepository.findByNomeContainingIgnoreCase(nome);
        }

        return clienteFornecedorRepository.findAll();
    }
}
