package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnidadeMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 5)
    private String codigo;

    @Column(nullable = false, length = 50)
    private String descricao;
}