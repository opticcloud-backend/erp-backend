package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrigemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;
}
