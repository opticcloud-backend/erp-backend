package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String sku;
    private String codigoBarras;
    private String ncm;
    private String cest;
    private String cfop;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private UnidadeMedida unidade;

    @ManyToOne
    @JoinColumn(name = "origem_id", nullable = false)
    private OrigemProduto origem;

    private Double custoReposicao;
    private Double lucroPercentual;
    private Double valorVenda;
    private String genero;
    private String material;

    @ManyToOne
    @JoinColumn(name = "tipo_produto_id", nullable = false)
    private TipoProduto tipoProduto;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProdutoTributacao tributacao;
}