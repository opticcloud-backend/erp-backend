package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios", schema = "opticcloud")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String password;

    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_funcao")
    private Funcao funcao;
}