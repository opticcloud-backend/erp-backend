package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.dto.ClienteRequestDTO;
import com.erpoticastec.backenderp.dto.FornecedorRequestDTO;
import com.erpoticastec.backenderp.model.Cliente;
import com.erpoticastec.backenderp.model.Fornecedor;
import com.erpoticastec.backenderp.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @Autowired
    FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<String> cadastrarFornecedor(@Valid @RequestBody FornecedorRequestDTO fornecedorRequestDTO) {
        fornecedorService.cadastrarFornecedor(fornecedorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Fornecedor cadastrado com sucesso.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateFornecedor(@Valid @RequestBody FornecedorRequestDTO fornecedorRequestDTO) {
        fornecedorService.updateFornecedor(fornecedorRequestDTO);
        return ResponseEntity.ok("Fornecedor atualizado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> buscarFornecedores(@RequestParam(required = false) String cnpj,
                                                               @RequestParam(required = false) String razaoSocial,
                                                               @RequestParam(required = false) String email,
                                                               @RequestParam(required = false) Long idOtica) {

        if (idOtica == null) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<Fornecedor> fornecedores = fornecedorService.buscarFornecedores(cnpj, razaoSocial, email, idOtica);
        if (fornecedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(fornecedores);
    }
}
