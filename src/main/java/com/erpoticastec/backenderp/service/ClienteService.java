package com.erpoticastec.backenderp.service;

import com.erpoticastec.backenderp.dto.ClienteRequestDTO;
import com.erpoticastec.backenderp.model.Cliente;
import com.erpoticastec.backenderp.model.Otica;
import com.erpoticastec.backenderp.model.TipoPessoa;
import com.erpoticastec.backenderp.model.Usuario;
import com.erpoticastec.backenderp.repository.ClienteRepository;

import java.time.LocalDateTime;

import com.erpoticastec.backenderp.repository.OticaRepository;
import com.erpoticastec.backenderp.repository.TipoPessoaRepository;
import com.erpoticastec.backenderp.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void cadastrarCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteFornecedor = new Cliente();
        clienteFornecedor.setNomeCompleto(clienteRequestDTO.nome());
        clienteFornecedor.setEmail(clienteRequestDTO.email());
        clienteFornecedor.setDocumento(clienteRequestDTO.documento());

        TipoPessoa tipoPessoa = tipoPessoaRepository.findById(clienteRequestDTO.tipoPessoaId())
                .orElseThrow(() -> new RuntimeException("Tipo de pessoa não encontrado"));
        clienteFornecedor.setTipoPessoa(tipoPessoa);

        Usuario usuarioCadastro = usuarioRepository.findById(clienteRequestDTO.usuarioCadastroId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        clienteFornecedor.setUsuario(usuarioCadastro);

        Otica otica = oticaRepository.findById(clienteRequestDTO.oticaId())
                .orElseThrow(() -> new RuntimeException("Ótica não encontrada"));
        clienteFornecedor.setOtica(otica);

        clienteFornecedor.setTipoPessoa(tipoPessoa);
        clienteFornecedor.setTelefone(clienteRequestDTO.telefone());
        clienteFornecedor.setEnderecoLogradouro(clienteRequestDTO.enderecoLogradouro());
        clienteFornecedor.setEnderecoNumero(clienteRequestDTO.enderecoNumero());
        clienteFornecedor.setEnderecoComplemento(clienteRequestDTO.enderecoComplemento());
        clienteFornecedor.setEnderecoBairro(clienteRequestDTO.enderecoBairro());
        clienteFornecedor.setEnderecoCidade(clienteRequestDTO.enderecoCidade());
        clienteFornecedor.setEnderecoEstado(clienteRequestDTO.enderecoEstado());
        clienteFornecedor.setEnderecoCep(clienteRequestDTO.enderecoCep());
        clienteFornecedor.setDataNascimento(clienteRequestDTO.dataNascimento());
        clienteFornecedor.setRazaoSocial(clienteRequestDTO.razaoSocial());
        clienteFornecedor.setNomeFantasia(clienteRequestDTO.nomeFantasia());
        clienteFornecedor.setInscricaoEstadual(clienteRequestDTO.inscricaoEstadual());
        clienteFornecedor.setResponsavelLegal(clienteRequestDTO.responsavelLegal());
        clienteFornecedor.setLimiteCredito(clienteRequestDTO.limiteCredito());
        clienteFornecedor.setFormaPagamentoPreferida(clienteRequestDTO.formaPagamentoPreferida());
        clienteFornecedor.setIndicadorCliente(clienteRequestDTO.indicadorCliente());
        clienteFornecedor.setPreferencias(clienteRequestDTO.preferencias());
        clienteFornecedor.setDataCadastro(LocalDateTime.now());
        clienteFornecedor.setAtivo(clienteRequestDTO.ativo());
        clienteFornecedor.setObservacoes(clienteRequestDTO.observacoes());

        clienteRepository.save(clienteFornecedor);
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

        if (pessoaUpdateDTO.tipoPessoaId() != null) {
            TipoPessoa tipoPessoa = tipoPessoaRepository.findById(pessoaUpdateDTO.tipoPessoaId())
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

        if (pessoaUpdateDTO.formaPagamentoPreferida() != null) {
            existingClienteFornecedor.setFormaPagamentoPreferida(pessoaUpdateDTO.formaPagamentoPreferida());
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
            return clienteRepository.findByNomeContainingIgnoreCaseAndOticaId(nome, idOtica);
        }

        return clienteRepository.findByOticaId(idOtica);
    }
}
