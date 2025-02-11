package com.erpoticastec.backenderp.infra.security.service;

import com.erpoticastec.backenderp.model.Usuario;
import com.erpoticastec.backenderp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUserWithRole(String login) {
        Usuario usuario = usuarioRepository.findByEmail(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return usuario;
    }
}
