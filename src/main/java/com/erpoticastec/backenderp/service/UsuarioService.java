package com.erpoticastec.backenderp.service;

import com.erpoticastec.backenderp.dto.RegisterRequestDTO;
import com.erpoticastec.backenderp.exceptions.UsernameNotFoundException;
import com.erpoticastec.backenderp.model.Role;
import com.erpoticastec.backenderp.model.Usuario;
import com.erpoticastec.backenderp.repository.RoleRepository;
import com.erpoticastec.backenderp.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Usuario criarUsuario(RegisterRequestDTO body) {
        Usuario newUsuario = new Usuario();
        newUsuario.setPassword(passwordEncoder.encode(body.password()));
        newUsuario.setEmail(body.email());
        newUsuario.setNome(body.nome());
        newUsuario.setSobrenome(body.sobrenome());

        Role role = roleRepository.findById(body.idfuncao())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        newUsuario.setRole(role);

        return usuarioRepository.save(newUsuario);
    }

    @Transactional
    public Usuario getUserWithRole(String login) {
        return usuarioRepository.findByEmail(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + login));
    }

    public Usuario updateUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUser(Long userId) {
        usuarioRepository.deleteById(userId);
    }
}

