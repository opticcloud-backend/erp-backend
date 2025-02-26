package com.erpoticastec.backenderp.dto;

public record ProdutoTributacaoDTO(
        IcmsSituacaoTributariaDTO icmsSituacaoTributaria,
        PisSituacaoTributariaDTO pisSituacaoTributaria,
        CofinsSituacaoTributariaDTO cofinsSituacaoTributaria,
        IpiSituacaoTributariaDTO ipiSituacaoTributaria,
        Double icmsAliquota,
        Double pisAliquota,
        Double cofinsAliquota,
        Double ipiAliquota
) {}