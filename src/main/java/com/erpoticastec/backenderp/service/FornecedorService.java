package com.erpoticastec.backenderp.service;

import com.erpoticastec.backenderp.dto.FornecedorRequestDTO;
import com.erpoticastec.backenderp.model.Fornecedor;
import com.erpoticastec.backenderp.model.Otica;
import com.erpoticastec.backenderp.model.Usuario;
import com.erpoticastec.backenderp.repository.FornecedorRepository;
import com.erpoticastec.backenderp.repository.OticaRepository;
import com.erpoticastec.backenderp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    OticaRepository oticaRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;

    public void cadastrarFornecedor(FornecedorRequestDTO fornecedorRequestDTO) {
        Fornecedor fornecedor = new Fornecedor();

        Usuario usuarioCadastro = usuarioRepository.findById(fornecedorRequestDTO.idUsuarioCadastro())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        fornecedor.setIdUsuarioCadastro(usuarioCadastro);

        Otica otica = oticaRepository.findById(fornecedorRequestDTO.idOtica())
                .orElseThrow(() -> new RuntimeException("Ótica não encontrada"));
        fornecedor.setIdOtica(otica);

        fornecedor.setRazaoSocial(fornecedorRequestDTO.razaoSocial());
        fornecedor.setNomeFantasia(fornecedorRequestDTO.nomeFantasia());
        fornecedor.setCnpj(fornecedorRequestDTO.cnpj());
        fornecedor.setEmail(fornecedorRequestDTO.email());
        fornecedor.setTelefone(fornecedorRequestDTO.telefone());
        fornecedor.setEnderecoLogradouro(fornecedorRequestDTO.enderecoLogradouro());
        fornecedor.setEnderecoNumero(fornecedorRequestDTO.enderecoNumero());
        fornecedor.setEnderecoComplemento(fornecedorRequestDTO.enderecoComplemento());
        fornecedor.setEnderecoBairro(fornecedorRequestDTO.enderecoBairro());
        fornecedor.setEnderecoCidade(fornecedorRequestDTO.enderecoCidade());
        fornecedor.setEnderecoEstado(fornecedorRequestDTO.enderecoEstado());
        fornecedor.setEnderecoCep(fornecedorRequestDTO.enderecoCep());
        fornecedor.setAtivo(fornecedorRequestDTO.ativo());
        fornecedor.setObservacoes(fornecedorRequestDTO.observacoes());
        fornecedor.setInscricaoEstadual(fornecedorRequestDTO.inscricaoEstadual());
        fornecedor.setPrazoPagamento(fornecedorRequestDTO.prazoPagamento());

        fornecedorRepository.save(fornecedor);
    }

    public void updateFornecedor(FornecedorRequestDTO fornecedorRequestDTO) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorRequestDTO.id())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        if (fornecedorRequestDTO.razaoSocial() != null) {
            fornecedor.setRazaoSocial(fornecedorRequestDTO.razaoSocial());
        }

        if (fornecedorRequestDTO.nomeFantasia() != null) {
            fornecedor.setNomeFantasia(fornecedorRequestDTO.nomeFantasia());
        }

        if (fornecedorRequestDTO.cnpj() != null) {
            fornecedor.setCnpj(fornecedorRequestDTO.cnpj());
        }

        if (fornecedorRequestDTO.email() != null) {
            fornecedor.setEmail(fornecedorRequestDTO.email());
        }

        if (fornecedorRequestDTO.telefone() != null) {
            fornecedor.setTelefone(fornecedorRequestDTO.telefone());
        }

        if (fornecedorRequestDTO.enderecoLogradouro() != null) {
            fornecedor.setEnderecoLogradouro(fornecedorRequestDTO.enderecoLogradouro());
        }

        if (fornecedorRequestDTO.enderecoNumero() != null) {
            fornecedor.setEnderecoNumero(fornecedorRequestDTO.enderecoNumero());
        }

        if (fornecedorRequestDTO.enderecoComplemento() != null) {
            fornecedor.setEnderecoComplemento(fornecedorRequestDTO.enderecoComplemento());
        }

        if (fornecedorRequestDTO.enderecoBairro() != null) {
            fornecedor.setEnderecoBairro(fornecedorRequestDTO.enderecoBairro());
        }

        if (fornecedorRequestDTO.enderecoCidade() != null) {
            fornecedor.setEnderecoCidade(fornecedorRequestDTO.enderecoCidade());
        }

        if (fornecedorRequestDTO.enderecoEstado() != null) {
            fornecedor.setEnderecoEstado(fornecedorRequestDTO.enderecoEstado());
        }

        if (fornecedorRequestDTO.enderecoCep() != null) {
            fornecedor.setEnderecoCep(fornecedorRequestDTO.enderecoCep());
        }

        if (fornecedorRequestDTO.ativo() != null) {
            fornecedor.setAtivo(fornecedorRequestDTO.ativo());
        }

        if (fornecedorRequestDTO.observacoes() != null) {
            fornecedor.setObservacoes(fornecedorRequestDTO.observacoes());
        }

        if (fornecedorRequestDTO.inscricaoEstadual() != null) {
            fornecedor.setInscricaoEstadual(fornecedorRequestDTO.inscricaoEstadual());
        }

        if (fornecedorRequestDTO.prazoPagamento() != null) {
            fornecedor.setPrazoPagamento(fornecedorRequestDTO.prazoPagamento());
        }

        fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> buscarFornecedores(String cnpj, String razaoSocial, String email, Long idOtica) {
        if (cnpj != null) {
            return fornecedorRepository.findByCnpjAndOticaId(cnpj, idOtica);
        }

        if (email != null) {
            return fornecedorRepository.findByEmailAndOticaId(email, idOtica);
        }

        if (razaoSocial != null) {
            return fornecedorRepository.findByRazaoSocialContainingIgnoreCaseAndOticaId(razaoSocial, idOtica);
        }

        return fornecedorRepository.findByOticaId(idOtica);
    }
}
