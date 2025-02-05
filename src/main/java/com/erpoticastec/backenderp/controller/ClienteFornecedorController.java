package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.dto.PessoaRequestDTO;
import com.erpoticastec.backenderp.model.ClienteFornecedor;
import com.erpoticastec.backenderp.service.ClienteFornecedorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes-fornecedores")
public class ClienteFornecedorController {

    @Autowired
    ClienteFornecedorService clienteFornecedorService;

    @PostMapping
    public ResponseEntity<String> cadastrarPessoa(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO) {
        clienteFornecedorService.cadastrarClienteFornecedor(pessoaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente/Fornecedor cadastrado com sucesso.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizarInformacoes(@Valid @RequestBody PessoaRequestDTO pessoaUpdateDTO) {
        clienteFornecedorService.updateClienteFornecedor(pessoaUpdateDTO);
        return ResponseEntity.ok("Cliente/Fornecedor atualizado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<ClienteFornecedor>> buscarClientes(@RequestParam(required = false) String documento,
                                                                  @RequestParam(required = false) String nome,
                                                                  @RequestParam(required = false) String email) {
        List<ClienteFornecedor> clienteFornecedores = clienteFornecedorService.buscarClientes(documento, nome, email);
        if (clienteFornecedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteFornecedores);
    }
}