package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "oticas", schema = "opticcloud")
public class Otica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fantasia", nullable = false, length = 255)
    private String nomeFantasia;

    @Column(name = "razao_social", nullable = false, length = 255)
    private String razaoSocial;

    @Column(name = "cnpj", nullable = false, unique = true, length = 18)
    private String cnpj;

    @Column(name = "inscricao_estadual", length = 50)
    private String inscricaoEstadual;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "endereco", nullable = false, length = 255)
    private String endereco;

    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @Column(name = "data_inicio_contrato", nullable = false)
    private LocalDate dataInicioContrato;

    @Column(name = "dia_pagamento", nullable = false)
    private Integer diaPagamento;

    @ManyToOne
    @JoinColumn(name = "id_plano_assinado", nullable = false)
    private Plano planoAssinado;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private StatusOtica status;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private ResponsavelOtica responsavel;

    @ManyToOne
    @JoinColumn(name = "id_forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logoUrl;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

