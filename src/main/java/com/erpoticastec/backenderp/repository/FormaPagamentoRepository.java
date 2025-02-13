package com.erpoticastec.backenderp.repository;

import com.erpoticastec.backenderp.model.FormaPagamento;
import com.erpoticastec.backenderp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    Optional<FormaPagamento> findByMetodo(String metodo);
}
