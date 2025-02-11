package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.dto.LoginRequestDTO;
import com.erpoticastec.backenderp.dto.RegisterRequestDTO;
import com.erpoticastec.backenderp.dto.ResponseDTO;
import com.erpoticastec.backenderp.dto.ResponseRegisterDTO;
import com.erpoticastec.backenderp.exceptions.InvalidCredentialsException;
import com.erpoticastec.backenderp.service.UserService;
import com.erpoticastec.backenderp.infra.security.service.TokenService;
import com.erpoticastec.backenderp.model.User;
import com.erpoticastec.backenderp.repository.RoleRepository;
import com.erpoticastec.backenderp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body){
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new InvalidCredentialsException("E-mail ou senha incorretos. Verifique suas credenciais, e tente novamente."));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new InvalidCredentialsException("E-mail ou senha incorretos. Verifique suas credenciais, e tente novamente.");
        }

        String token = this.tokenService.generateToken(user.getEmail());
        List<Long> oticaIds = userRepository.findOticaIdsByUsuario(user.getId());
        return ResponseEntity.ok(new ResponseDTO(user.getEmail(), token, oticaIds));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(body.email()))) {
            return ResponseEntity.badRequest().body("Usuario ja cadastrado!");
        }

        User newUser = userService.criarUsuario(body);
        String token = tokenService.generateToken(newUser.getEmail());

        return ResponseEntity.ok(new ResponseRegisterDTO(newUser.getEmail(), token));
    }
}
