package com.erpoticastec.backenderp.dto;

public record ProdutoDTO(String nome, String descricao, String sku, String codigoBarras, String ncm, String cest, String cfop, Long unidadeId, Long origemId, Double custoReposicao, Double lucroPercentual, Double valorVenda, String genero, String material, Long tipoProdutoId, ProdutoTributacaoDTO tributacao) {
}
