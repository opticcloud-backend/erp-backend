package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.dto.ProdutoDTO;
import com.erpoticastec.backenderp.model.Produto;
import com.erpoticastec.backenderp.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoService.salvarProdutoComTributacao(produtoDTO);
        return ResponseEntity.ok(produto);
    }
}