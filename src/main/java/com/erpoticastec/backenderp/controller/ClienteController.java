package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.dto.ClienteRequestDTO;
import com.erpoticastec.backenderp.exceptions.ClienteJaCadastradoException;
import com.erpoticastec.backenderp.exceptions.InvalidCredentialsException;
import com.erpoticastec.backenderp.model.Cliente;
import com.erpoticastec.backenderp.repository.ClienteRepository;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        logger.info("Requisição para cadastrar cliente do idOtica: {}, ClienteRequestDTO: {}",
                clienteRequestDTO.oticaId(), clienteRequestDTO);

        List<Cliente> clientesExistentes = clienteRepository.findByDocumentoAndOticaId(clienteRequestDTO.documento(), clienteRequestDTO.oticaId())
                .orElse(Collections.emptyList());

        if (!clientesExistentes.isEmpty()) {
            logger.warn("Cliente com documento {} já cadastrado anteriormente", clienteRequestDTO.documento());
            throw new ClienteJaCadastradoException("Cliente com documento " + clienteRequestDTO.documento() + " já cadastrado anteriormente");
        }

        clienteService.cadastrarCliente(clienteRequestDTO);

        logger.info("Cliente do idOtica {} cadastrado com sucesso: {}", clienteRequestDTO.oticaId(), clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso.");
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizarInformacoes(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO pessoaUpdateDTO) {
        logger.info("Recebida requisição para atualizar cliente do idOtica {}: {}", id, pessoaUpdateDTO);

        clienteService.updateCliente(pessoaUpdateDTO);
        
        logger.info("Cliente do idOtica {} atualizado com sucesso: {}", id, pessoaUpdateDTO);
        return ResponseEntity.ok("Cliente atualizado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<?> buscarClientes(@RequestParam(required = false) String documento,
                                                                  @RequestParam(required = false) String nome,
                                                                  @RequestParam(required = false) String email,
                                                                  @RequestParam(required = true) Long idOtica) {
        logger.info("Recebida requisição para buscar clientes do idOtica: {}, documento: {}, nome: {}, email: {}", idOtica, documento, nome, email);

        if (idOtica == null) {
            logger.warn("Parâmetro idOtica é obrigatório, mas não foi fornecido");
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        try {
            Optional<List<Cliente>> clienteFornecedores = clienteService.buscarClientes(documento, nome, email, idOtica);

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