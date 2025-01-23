package com.erpoticastec.backenderp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erpoticastec.backenderp.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);
}