package com.erpoticastec.backenderp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.erpoticastec.backenderp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query("SELECT uo.otica.id FROM UsuarioOtica uo WHERE uo.usuario.id = :usuarioId")
    List<Long> findOticaIdsByUsuario(@Param("usuarioId") Long usuarioId);
}