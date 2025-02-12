package com.erpoticastec.backenderp.repository;

import com.erpoticastec.backenderp.model.Cliente;
import com.erpoticastec.backenderp.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    List<Fornecedor> findByCnpjAndOticaId(String cnpj, Long idOtica);

    List<Fornecedor> findByEmailAndOticaId(String email, Long idOtica);

    List<Fornecedor> findByRazaoSocialContainingIgnoreCaseAndOticaId(String razaoSocial, Long idOtica);

    List<Fornecedor> findByOticaId(Long idOtica);
}
