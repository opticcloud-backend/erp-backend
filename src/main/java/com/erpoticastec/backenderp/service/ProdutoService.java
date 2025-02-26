package com.erpoticastec.backenderp.service;

import com.erpoticastec.backenderp.dto.ProdutoDTO;
import com.erpoticastec.backenderp.model.*;
import com.erpoticastec.backenderp.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoTributacaoRepository tributacaoRepository;

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    @Autowired
    private OrigemProdutoRepository origemProdutoRepository;

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    @Autowired
    private IcmsSituacaoTributariaRepository icmsSituacaoTributariaRepository;

    @Autowired
    private PisSituacaoTributariaRepository pisSituacaoTributariaRepository;

    @Autowired
    private CofinsSituacaoTributariaRepository cofinsSituacaoTributariaRepository;

    @Autowired
    private IpiSituacaoTributariaRepository ipiSituacaoTributariaRepository;

    @Transactional
    public Produto salvarProdutoComTributacao(ProdutoDTO produtoDTO) {
        UnidadeMedida unidade = unidadeMedidaRepository.findById(produtoDTO.unidadeId())
                .orElseThrow(() -> new EntityNotFoundException("Unidade de medida nao encontrada"));

        OrigemProduto origem = origemProdutoRepository.findById(produtoDTO.origemId())
                .orElseThrow(() -> new EntityNotFoundException("Origem do produto nao encontrada"));

        TipoProduto tipoProduto = tipoProdutoRepository.findById(produtoDTO.tipoProdutoId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de produto nao encontrado"));

        // Cria e salva o produto
        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setDescricao(produtoDTO.descricao());
        produto.setSku(produtoDTO.sku());
        produto.setCodigoBarras(produtoDTO.codigoBarras());
        produto.setNcm(produtoDTO.ncm());
        produto.setCest(produtoDTO.cest());
        produto.setCfop(produtoDTO.cfop());
        produto.setUnidade(unidade);
        produto.setOrigem(origem);
        produto.setCustoReposicao(produtoDTO.custoReposicao());
        produto.setLucroPercentual(produtoDTO.lucroPercentual());
        produto.setValorVenda(produtoDTO.valorVenda());
        produto.setGenero(produtoDTO.genero());
        produto.setMaterial(produtoDTO.material());
        produto.setTipoProduto(tipoProduto);

        Produto produtoSalvo = produtoRepository.save(produto);

        // Busca as entidades tributárias
        IcmsSituacaoTributaria icms = icmsSituacaoTributariaRepository.findById(produtoDTO.tributacao().icmsSituacaoTributaria().id())
                .orElseThrow(() -> new EntityNotFoundException("Situação tributária do ICMS não encontrada"));

        PisSituacaoTributaria pis = pisSituacaoTributariaRepository.findById(produtoDTO.tributacao().pisSituacaoTributaria().id())
                .orElseThrow(() -> new EntityNotFoundException("Situação tributária do PIS não encontrada"));

        CofinsSituacaoTributaria cofins = cofinsSituacaoTributariaRepository.findById(produtoDTO.tributacao().cofinsSituacaoTributaria().id())
                .orElseThrow(() -> new EntityNotFoundException("Situação tributária do COFINS não encontrada"));

        IpiSituacaoTributaria ipi = ipiSituacaoTributariaRepository.findById(produtoDTO.tributacao().ipiSituacaoTributaria().id())
                .orElseThrow(() -> new EntityNotFoundException("Situação tributária do IPI não encontrada"));

        // Cria e salva a tributação
        ProdutoTributacao tributacao = new ProdutoTributacao();
        tributacao.setProduto(produtoSalvo);
        tributacao.setIcmsSituacaoTributaria(icms);
        tributacao.setPisSituacaoTributaria(pis);
        tributacao.setCofinsSituacaoTributaria(cofins);
        tributacao.setIpiSituacaoTributaria(ipi);
        tributacao.setIcmsAliquota(produtoDTO.tributacao().icmsAliquota());
        tributacao.setPisAliquota(produtoDTO.tributacao().pisAliquota());
        tributacao.setCofinsAliquota(produtoDTO.tributacao().cofinsAliquota());
        tributacao.setIpiAliquota(produtoDTO.tributacao().ipiAliquota());

        tributacaoRepository.save(tributacao);

        return produtoSalvo;
    }
}