package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class IcmsSituacaoTributaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 3)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String descricao;
}