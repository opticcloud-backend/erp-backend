package com.erpoticastec.backenderp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

public record PessoaRequestDTO(
        Long id,
        @Size(max = 255, message = "O nome não pode exceder 255 caracteres.") String nome,
        Long tipoPessoaId,
        @Pattern(regexp = "\\d{11}|\\d{14}", message = "O documento deve ter 11 (CPF) ou 14 (CNPJ) dígitos.") String documento,
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
        @Size(max = 500, message = "As observações não podem exceder 500 caracteres.") String observacoes
) {
}
