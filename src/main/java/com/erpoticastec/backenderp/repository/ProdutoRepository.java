package com.erpoticastec.backenderp.repository;

import com.erpoticastec.backenderp.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}