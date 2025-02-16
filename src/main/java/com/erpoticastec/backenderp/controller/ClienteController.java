package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.dto.ClienteRequestDTO;
import com.erpoticastec.backenderp.model.Cliente;
import com.erpoticastec.backenderp.service.ClienteService;
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
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<String> cadastrarCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        logger.info("Requisição para cadastrar cliente do idOtica: {}, ClienteRequestDTO: {}",
                clienteRequestDTO.oticaId(), clienteRequestDTO);

        clienteService.cadastrarCliente(clienteRequestDTO);

        logger.info("Cliente do idOtica {} cadastrado com sucesso: {}", clienteRequestDTO.oticaId(), clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizarInformacoes(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO pessoaUpdateDTO) {
        logger.info("Recebida requisição para atualizar cliente idOtica {}: {}", id, pessoaUpdateDTO);
        try {
            clienteService.updateCliente(pessoaUpdateDTO);
            logger.info("Cliente do idOtica {} atualizado com sucesso: {}", id, pessoaUpdateDTO);
            return ResponseEntity.ok("Cliente atualizado com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao atualizar cliente do idOtica {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar cliente.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarClientes(@RequestParam(required = false) String documento,
                                                        @RequestParam(required = false) String nome,
                                                        @RequestParam(required = false) String email,
                                                        @RequestParam(required = true) Long idOtica) {
        logger.info("Recebida requisição para buscar clientes do idOtica: {}, documento: {}, nome: {}, email: {}", idOtica, documento, nome, email);

        if (idOtica == null) {
            logger.warn("Parâmetro idOtica é obrigatório, mas não foi fornecido");
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        try {
            List<Cliente> clienteFornecedores = clienteService.buscarClientes(documento, nome, email, idOtica);

            if (clienteFornecedores.isEmpty()) {
                logger.info("Nenhum cliente do IdOtica {} encontrado para os parâmetros fornecidos.", idOtica);
                return ResponseEntity.noContent().build();
            }

            logger.info("Cliente(s) do IdOtica {} encontrado(s): {}", idOtica, clienteFornecedores);
            return ResponseEntity.ok(clienteFornecedores);
        } catch (Exception e) {
            logger.error("Erro ao buscar clientes do IdOtica {}: {}" , idOtica, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}