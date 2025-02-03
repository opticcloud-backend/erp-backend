package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

@Table(name = "funcoes")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String description;

    @Column(name = "name")
    private String nameRole;
}

