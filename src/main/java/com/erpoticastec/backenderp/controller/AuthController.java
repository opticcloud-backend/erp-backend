package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.dto.LoginRequestDTO;
import com.erpoticastec.backenderp.dto.RegisterRequestDTO;
import com.erpoticastec.backenderp.dto.ResponseDTO;
import com.erpoticastec.backenderp.exceptions.InvalidCredentialsException;
import com.erpoticastec.backenderp.service.UserService;
import com.erpoticastec.backenderp.infra.security.TokenService;
import com.erpoticastec.backenderp.model.Role;
import com.erpoticastec.backenderp.model.User;
import com.erpoticastec.backenderp.repository.RoleRepository;
import com.erpoticastec.backenderp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository repository;
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new InvalidCredentialsException("E-mail ou senha incorretos. Verifique suas credenciais, e tente novamente."));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new InvalidCredentialsException("E-mail ou senha incorretos. Verifique suas credenciais, e tente novamente.");
        }

        String token = this.tokenService.generateToken(user);
        return ResponseEntity.ok(new ResponseDTO(user.getEmail(), token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = this.repository.findByEmail(body.email());

        if (user.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setNome(body.nome());
        newUser.setSobrenome(body.sobrenome());

        Role role = roleRepository.findById(body.idfuncao())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        newUser.setRole(role);

        this.repository.save(newUser);

        String token = this.tokenService.generateToken(newUser);
        return ResponseEntity.ok(new ResponseDTO(newUser.getEmail(), token));
    }
}
