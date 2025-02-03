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

    @PostMapping("/registrar")
    public ResponseEntity<String> cadastrarPessoa(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO) {
        clienteFornecedorService.cadastrarClienteFornecedor(pessoaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente/Fornecedor cadastrado com sucesso.");
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<String> atualizarInformacoes(@Valid @RequestBody PessoaRequestDTO pessoaUpdateDTO) {
        clienteFornecedorService.updateClienteFornecedor(pessoaUpdateDTO);
        return ResponseEntity.ok("Cliente/Fornecedor atualizado com sucesso.");
    }

    @GetMapping("/buscar/documento/{documento}")
    public ResponseEntity<ClienteFornecedor> buscarPorDocumento(@PathVariable String documento) {
        ClienteFornecedor clienteFornecedor = clienteFornecedorService.buscarPorCpfCnpj(documento)
                .orElseThrow(() -> new EntityNotFoundException("Cliente/Fornecedor não encontrado"));

        return ResponseEntity.ok(clienteFornecedor);
    }

    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<List<ClienteFornecedor>> buscarPorNome(@PathVariable String nome) {
        List<ClienteFornecedor> clienteFornecedores = clienteFornecedorService.buscarPorNome(nome);

        if (clienteFornecedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(clienteFornecedores);
    }

    @GetMapping("/buscar/email/{email}")
    public ResponseEntity<ClienteFornecedor> buscarPorEmail(@PathVariable String email) {
        ClienteFornecedor clienteFornecedor = clienteFornecedorService.buscarPorEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Cliente/Fornecedor não encontrado"));

        return ResponseEntity.ok(clienteFornecedor);
    }
}
