package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "fornecedor", schema = "opticcloud")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "otica_id", nullable = false, referencedColumnName = "id")
    private Otica idOtica;

    @ManyToOne
    @JoinColumn(name = "usuario_cadastro_id", nullable = false, referencedColumnName = "id")
    private Usuario idUsuarioCadastro;

    @Column(name = "razao_social", nullable = false, length = 255)
    private String razaoSocial;

    @Column(name = "nome_fantasia", length = 255)
    private String nomeFantasia;

    @Column(name = "cnpj", nullable = false, unique = true, length = 18)
    private String cnpj;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "endereco_logradouro", length = 255)
    private String enderecoLogradouro;

    @Column(name = "endereco_numero", length = 20)
    private String enderecoNumero;

    @Column(name = "endereco_complemento", length = 255)
    private String enderecoComplemento;

    @Column(name = "endereco_bairro", length = 255)
    private String enderecoBairro;

    @Column(name = "endereco_cidade", length = 100)
    private String enderecoCidade;

    @Column(name = "endereco_estado", length = 50)
    private String enderecoEstado;

    @Column(name = "endereco_cep", length = 20)
    private String enderecoCep;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "observacoes", length = 255)
    private String observacoes;

    @Column(name = "inscricao_estadual", length = 20)
    private String inscricaoEstadual;

    @Column(name = "prazo_pagamento")
    private Integer prazoPagamento;
}
