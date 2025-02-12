package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "cliente", schema = "opticcloud")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario_cadastro", nullable = false, referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_otica", nullable = false, referencedColumnName = "id")
    private Otica otica;

    @ManyToOne
    @JoinColumn(name = "id_tipo_pessoa", nullable = false, referencedColumnName = "id")
    private TipoPessoa tipoPessoa;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "documento", nullable = false, unique = true, length = 20)
    private String documento;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(name = "inscricao_estadual", length = 20)
    private String inscricaoEstadual;

    @Column(name = "responsavel_legal")
    private String responsavelLegal;

    @Column(name = "endereco_logradouro")
    private String enderecoLogradouro;

    @Column(name = "endereco_numero", length = 20)
    private String enderecoNumero;

    @Column(name = "endereco_complemento")
    private String enderecoComplemento;

    @Column(name = "endereco_bairro")
    private String enderecoBairro;

    @Column(name = "endereco_cidade", length = 100)
    private String enderecoCidade;

    @Column(name = "endereco_estado", length = 50)
    private String enderecoEstado;

    @Column(name = "endereco_cep", length = 20)
    private String enderecoCep;

    @Column(name = "limite_credito", precision = 10, scale = 2)
    private BigDecimal limiteCredito;

    @ManyToOne
    @JoinColumn(name = "id_forma_pagamento_preferida", nullable = false, referencedColumnName = "id")
    private FormaPagamento formaPagamentoPreferida;

    @Column(name = "indicador_cliente")
    private String indicadorCliente;

    @Column(name = "preferencias")
    private String preferencias;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "observacoes")
    private String observacoes;
}