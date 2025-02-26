package com.erpoticastec.backenderp.repository;

import com.erpoticastec.backenderp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<List<Cliente>> findByDocumentoAndOticaId(String documento, Long idOtica);

    Optional<List<Cliente>> findByEmailAndOticaId(String email, Long idOtica);

    Optional<List<Cliente>> findByNomeCompletoContainingIgnoreCaseAndOticaId(String nomeCompleto, Long idOtica);

    Optional<List<Cliente>> findByOticaId(Long idOtica);
}
