package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "responsaveis_oticas", schema = "opticcloud")
public class ResponsavelOtica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false, length = 255)
    private String nomeCompleto;

    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;
}