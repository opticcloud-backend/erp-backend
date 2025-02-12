package com.erpoticastec.backenderp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erpoticastec.backenderp.model.Funcao;

import java.util.Optional;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {
    Optional<Funcao> findById(Long id);
}