package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cofins_situacao_tributaria", schema = "opticcloud")
public class CofinsSituacaoTributaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 2)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String descricao;
}