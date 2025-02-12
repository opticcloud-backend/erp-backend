package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.dto.ClienteRequestDTO;
import com.erpoticastec.backenderp.model.Cliente;
import com.erpoticastec.backenderp.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<String> cadastrarCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        clienteService.cadastrarCliente(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizarInformacoes(@Valid @RequestBody ClienteRequestDTO pessoaUpdateDTO) {
        clienteService.updateCliente(pessoaUpdateDTO);
        return ResponseEntity.ok("Cliente atualizado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarClientes(@RequestParam(required = false) String documento,
                                                        @RequestParam(required = false) String nome,
                                                        @RequestParam(required = false) String email,
                                                        @RequestParam(required = true) Long idOtica) {
        if (idOtica == null) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<Cliente> clienteFornecedores = clienteService.buscarClientes(documento, nome, email, idOtica);
        if (clienteFornecedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(clienteFornecedores);
    }
}