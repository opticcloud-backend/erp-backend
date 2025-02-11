package com.erpoticastec.backenderp.dto;

import jakarta.validation.constraints.*;

public record FornecedorRequestDTO(
        Long id,
        @NotNull(message = "O ID da ótica não pode ser nulo.") Long idOtica,
        @NotNull(message = "O ID do usuário de cadastro não pode ser nulo.") Long idUsuarioCadastro,
        @NotNull(message = "A razão social não pode ser nula.") @Size(max = 255, message = "A razão social não pode exceder 255 caracteres.") String razaoSocial,
        @Size(max = 255, message = "O nome fantasia não pode exceder 255 caracteres.") String nomeFantasia,
        @NotNull(message = "O CNPJ não pode ser nulo.") @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "O CNPJ deve ser válido.") String cnpj,
        @Email(message = "O e-mail deve ser válido.") @Size(max = 255, message = "O e-mail não pode exceder 255 caracteres.") String email,
        @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve seguir o formato (XX) XXXXX-XXXX.") String telefone,
        @Size(max = 255, message = "O logradouro não pode exceder 255 caracteres.") String enderecoLogradouro,
        @Size(max = 20, message = "O número não pode exceder 20 caracteres.") String enderecoNumero,
        @Size(max = 255, message = "O complemento não pode exceder 255 caracteres.") String enderecoComplemento,
        @Size(max = 255, message = "O bairro não pode exceder 255 caracteres.") String enderecoBairro,
        @Size(max = 100, message = "A cidade não pode exceder 100 caracteres.") String enderecoCidade,
        @Size(max = 50, message = "O estado não pode exceder 50 caracteres.") String enderecoEstado,
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve seguir o formato XXXXX-XXX.") String enderecoCep,
        Boolean ativo,
        @Size(max = 255, message = "As observações não podem exceder 255 caracteres.") String observacoes,
        @Size(max = 20, message = "A inscrição estadual não pode exceder 20 caracteres.") String inscricaoEstadual,
        Integer prazoPagamento
) {}

