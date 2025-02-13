package com.erpoticastec.backenderp.service;

import com.erpoticastec.backenderp.dto.ClienteRequestDTO;
import com.erpoticastec.backenderp.model.*;
import com.erpoticastec.backenderp.repository.*;

import java.time.LocalDateTime;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    TipoPessoaRepository tipoPessoaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    OticaRepository oticaRepository;

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    public void cadastrarCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();
        cliente.setNomeCompleto(clienteRequestDTO.nome());
        cliente.setEmail(clienteRequestDTO.email());
        cliente.setDocumento(clienteRequestDTO.documento());

        TipoPessoa tipoPessoa = tipoPessoaRepository.findByDescricao(clienteRequestDTO.tipoCliente())
                .orElseThrow(() -> new RuntimeException("Tipo de pessoa nao encontrado"));
        cliente.setTipoPessoa(tipoPessoa);

        Usuario usuarioCadastro = usuarioRepository.findByEmail(clienteRequestDTO.emailUsuarioCadastro())
                .orElseThrow(() -> new RuntimeException("Usuario ao encontrado"));
        cliente.setUsuario(usuarioCadastro);

        Otica otica = oticaRepository.findById(clienteRequestDTO.oticaId())
                .orElseThrow(() -> new RuntimeException("Otica nao encontrada"));
        cliente.setOtica(otica);

        cliente.setTelefone(clienteRequestDTO.telefone());
        cliente.setEnderecoLogradouro(clienteRequestDTO.enderecoLogradouro());
        cliente.setEnderecoNumero(clienteRequestDTO.enderecoNumero());
        cliente.setEnderecoComplemento(clienteRequestDTO.enderecoComplemento());
        cliente.setEnderecoBairro(clienteRequestDTO.enderecoBairro());
        cliente.setEnderecoCidade(clienteRequestDTO.enderecoCidade());
        cliente.setEnderecoEstado(clienteRequestDTO.enderecoEstado());
        cliente.setEnderecoCep(clienteRequestDTO.enderecoCep());
        cliente.setDataNascimento(clienteRequestDTO.dataNascimento());
        cliente.setRazaoSocial(clienteRequestDTO.razaoSocial());
        cliente.setNomeFantasia(clienteRequestDTO.nomeFantasia());
        cliente.setInscricaoEstadual(clienteRequestDTO.inscricaoEstadual());
        cliente.setResponsavelLegal(clienteRequestDTO.responsavelLegal());
        cliente.setLimiteCredito(clienteRequestDTO.limiteCredito());

        FormaPagamento formaPagamento = formaPagamentoRepository.findByMetodo(clienteRequestDTO.metodoPagamentoPreferido())
                .orElseThrow(() -> new EntityNotFoundException("Forma de pagamento nao encontrada"));
        cliente.setFormaPagamentoPreferida(formaPagamento);

        cliente.setIndicadorCliente(clienteRequestDTO.indicadorCliente());
        cliente.setPreferencias(clienteRequestDTO.preferencias());
        cliente.setDataCadastro(LocalDateTime.now());
        cliente.setAtivo(clienteRequestDTO.ativo());
        cliente.setObservacoes(clienteRequestDTO.observacoes());

        clienteRepository.save(cliente);
    }

    public void updateCliente(ClienteRequestDTO pessoaUpdateDTO) {
        Cliente existingClienteFornecedor = clienteRepository.findById(pessoaUpdateDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        if (pessoaUpdateDTO.nome() != null) {
            existingClienteFornecedor.setNomeCompleto(pessoaUpdateDTO.nome());
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

        if (pessoaUpdateDTO.tipoCliente() != null) {
            TipoPessoa tipoPessoa = tipoPessoaRepository.findByDescricao(pessoaUpdateDTO.tipoCliente())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de Pessoa não encontrado"));
            existingClienteFornecedor.setTipoPessoa(tipoPessoa);
        }

        if (pessoaUpdateDTO.dataNascimento() != null) {
            existingClienteFornecedor.setDataNascimento(pessoaUpdateDTO.dataNascimento());
        }

        if (pessoaUpdateDTO.razaoSocial() != null) {
            existingClienteFornecedor.setRazaoSocial(pessoaUpdateDTO.razaoSocial());
        }

        if (pessoaUpdateDTO.nomeFantasia() != null) {
            existingClienteFornecedor.setNomeFantasia(pessoaUpdateDTO.nomeFantasia());
        }

        if (pessoaUpdateDTO.inscricaoEstadual() != null) {
            existingClienteFornecedor.setInscricaoEstadual(pessoaUpdateDTO.inscricaoEstadual());
        }

        if (pessoaUpdateDTO.responsavelLegal() != null) {
            existingClienteFornecedor.setResponsavelLegal(pessoaUpdateDTO.responsavelLegal());
        }

        if (pessoaUpdateDTO.limiteCredito() != null) {
            existingClienteFornecedor.setLimiteCredito(pessoaUpdateDTO.limiteCredito());
        }

        if (pessoaUpdateDTO.metodoPagamentoPreferido() != null) {
            FormaPagamento formaPagamento = formaPagamentoRepository.findByMetodo(pessoaUpdateDTO.metodoPagamentoPreferido())
                    .orElseThrow(() -> new EntityNotFoundException("Forma de pagamento nao encontrado"));

            existingClienteFornecedor.setFormaPagamentoPreferida(formaPagamento);
        }

        if (pessoaUpdateDTO.indicadorCliente() != null) {
            existingClienteFornecedor.setIndicadorCliente(pessoaUpdateDTO.indicadorCliente());
        }

        if (pessoaUpdateDTO.preferencias() != null) {
            existingClienteFornecedor.setPreferencias(pessoaUpdateDTO.preferencias());
        }

        clienteRepository.save(existingClienteFornecedor);
    }


    public List<Cliente> buscarClientes(String documento, String nome, String email, Long idOtica) {
        if (documento != null) {
            return clienteRepository.findByDocumentoAndOticaId(documento, idOtica);
        }

        if (email != null) {
            return clienteRepository.findByEmailAndOticaId(email, idOtica);
        }

        if (nome != null) {
            return clienteRepository.findByNomeCompletoContainingIgnoreCaseAndOticaId(nome, idOtica);
        }

        return Collections.emptyList();
    }
}
