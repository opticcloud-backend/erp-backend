package com.erpoticastec.backenderp.repository;

import com.erpoticastec.backenderp.model.ClienteFornecedor;
import com.erpoticastec.backenderp.model.TipoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoPessoaRepository extends JpaRepository<TipoPessoa, Long> {
    Optional<TipoPessoa> findByDescricao(String descricao);
}

