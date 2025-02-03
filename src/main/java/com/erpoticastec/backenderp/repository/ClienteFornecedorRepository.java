package com.erpoticastec.backenderp.repository;

import com.erpoticastec.backenderp.model.ClienteFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ClienteFornecedorRepository extends JpaRepository<ClienteFornecedor, Long> {

    Optional<ClienteFornecedor> findByDocumento(String documento);

    List<ClienteFornecedor> findByNomeContainingIgnoreCase(String nome);

    Optional<ClienteFornecedor> findByEmail(String email);
}
