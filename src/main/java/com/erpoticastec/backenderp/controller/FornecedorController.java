package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.dto.FornecedorRequestDTO;
import com.erpoticastec.backenderp.model.Fornecedor;
import com.erpoticastec.backenderp.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private static final Logger logger = LoggerFactory.getLogger(FornecedorController.class);

    @Autowired
    FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<String> cadastrarFornecedor(@Valid @RequestBody FornecedorRequestDTO fornecedorRequestDTO) {
        logger.info("Requisição para cadastrar fornecedor do idOtica: {}, FornecedorRequestDTO: {}", fornecedorRequestDTO.idOtica(), fornecedorRequestDTO);
        try {
            fornecedorService.cadastrarFornecedor(fornecedorRequestDTO);
            logger.info("Fornecedor do idOtica {} cadastrado com sucesso: {}", fornecedorRequestDTO.idOtica(), fornecedorRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Fornecedor cadastrado com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao cadastrar fornecedor do idOtica {}: {}", fornecedorRequestDTO.idOtica(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar fornecedor.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizarInformacoes(@PathVariable Long id, @Valid @RequestBody FornecedorRequestDTO fornecedorUpdateDTO) {
        logger.info("Requisição para atualizar fornecedor do idOtica {}: {}", id, fornecedorUpdateDTO);
        try {
            fornecedorService.updateFornecedor(fornecedorUpdateDTO);
            logger.info("Fornecedor do idOtica {} atualizado com sucesso: {}", id, fornecedorUpdateDTO);
            return ResponseEntity.ok("Fornecedor atualizado com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao atualizar fornecedor do idOtica {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar fornecedor.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> buscarFornecedores(@RequestParam(required = false) String documento,
                                                               @RequestParam(required = false) String nome,
                                                               @RequestParam(required = false) String email,
                                                               @RequestParam(required = true) Long idOtica) {
        logger.info("Recebida requisição para buscar fornecedores do idOtica: {}, documento: {}, nome: {}, email: {}", idOtica, documento, nome, email);

        if (idOtica == null) {
            logger.warn("Parâmetro idOtica é obrigatório, mas não foi fornecido");
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        try {
            List<Fornecedor> fornecedores = fornecedorService.buscarFornecedores(documento, nome, email, idOtica);

            if (fornecedores.isEmpty()) {
                logger.info("Nenhum fornecedor encontrado para os parâmetros fornecidos do idOtica {}.", idOtica);
                return ResponseEntity.noContent().build();
            }

            logger.info("Fornecedores do idOtica {} encontrados: {}", idOtica, fornecedores);
            return ResponseEntity.ok(fornecedores);
        } catch (Exception e) {
            logger.error("Erro ao buscar fornecedores do idOtica {}: {}", idOtica, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}