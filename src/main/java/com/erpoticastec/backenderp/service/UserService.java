package com.erpoticastec.backenderp.service;

import com.erpoticastec.backenderp.dto.RegisterRequestDTO;
import com.erpoticastec.backenderp.exceptions.UsernameNotFoundException;
import com.erpoticastec.backenderp.model.Role;
import com.erpoticastec.backenderp.model.User;
import com.erpoticastec.backenderp.repository.RoleRepository;
import com.erpoticastec.backenderp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User criarUsuario(RegisterRequestDTO body) {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setNome(body.nome());
        newUser.setSobrenome(body.sobrenome());

        Role role = roleRepository.findById(body.idfuncao())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        newUser.setRole(role);

        return userRepository.save(newUser);
    }

    @Transactional
    public User getUserWithRole(String login) {
        return userRepository.findByEmail(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + login));
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

