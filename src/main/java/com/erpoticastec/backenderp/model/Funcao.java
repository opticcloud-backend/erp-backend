package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "funcoes", schema = "opticcloud")
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String description;

    @Column(name = "name")
    private String nameRole;
}

