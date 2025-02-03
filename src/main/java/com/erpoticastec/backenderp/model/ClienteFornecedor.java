package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pessoa", schema = "opticcloud")
public class ClienteFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false, referencedColumnName = "id")
    private TipoPessoa tipoPessoa;

    @Column(name = "documento", nullable = false, unique = true, length = 20)
    private String documento;

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
    private Boolean ativo = true;

    @Column(name = "observacoes")
    private String observacoes;
}
