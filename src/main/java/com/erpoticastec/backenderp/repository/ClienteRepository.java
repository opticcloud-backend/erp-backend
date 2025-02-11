package com.erpoticastec.backenderp.repository;

import com.erpoticastec.backenderp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByDocumentoAndOticaId(String documento, Long idOtica);

    List<Cliente> findByEmailAndOticaId(String email, Long idOtica);

    List<Cliente> findByNomeContainingIgnoreCaseAndOticaId(String nome, Long idOtica);

    List<Cliente> findByOticaId(Long idOtica);
}
