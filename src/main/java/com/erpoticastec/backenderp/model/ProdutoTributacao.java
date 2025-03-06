package com.erpoticastec.backenderp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "produto_tributacao", schema = "opticcloud")
public class ProdutoTributacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "icms_situacao_tributaria_id", nullable = false)
    private IcmsSituacaoTributaria icmsSituacaoTributaria;

    @ManyToOne
    @JoinColumn(name = "pis_situacao_tributaria_id", nullable = false)
    private PisSituacaoTributaria pisSituacaoTributaria;

    @ManyToOne
    @JoinColumn(name = "cofins_situacao_tributaria_id", nullable = false)
    private CofinsSituacaoTributaria cofinsSituacaoTributaria;

    @ManyToOne
    @JoinColumn(name = "ipi_situacao_tributaria_id", nullable = false)
    private IpiSituacaoTributaria ipiSituacaoTributaria;

    private Double icmsAliquota;
    private Double pisAliquota;
    private Double cofinsAliquota;
    private Double ipiAliquota;
}
