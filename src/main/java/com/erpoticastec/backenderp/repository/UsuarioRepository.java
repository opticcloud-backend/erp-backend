package com.erpoticastec.backenderp.repository;

import java.util.List;
import java.util.Optional;

import com.erpoticastec.backenderp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query("SELECT uo.otica.id FROM UsuarioOtica uo WHERE uo.usuario.id = :usuarioId")
    List<Long> findOticaIdsByUsuario(@Param("usuarioId") Long usuarioId);
}