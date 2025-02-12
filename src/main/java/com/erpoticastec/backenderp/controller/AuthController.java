package com.erpoticastec.backenderp.controller;

import com.erpoticastec.backenderp.dto.LoginRequestDTO;
import com.erpoticastec.backenderp.dto.RegisterRequestDTO;
import com.erpoticastec.backenderp.dto.ResponseDTO;
import com.erpoticastec.backenderp.dto.ResponseRegisterDTO;
import com.erpoticastec.backenderp.exceptions.InvalidCredentialsException;
import com.erpoticastec.backenderp.model.Usuario;
import com.erpoticastec.backenderp.service.UsuarioService;
import com.erpoticastec.backenderp.infra.security.service.TokenService;
import com.erpoticastec.backenderp.repository.FuncaoRepository;
import com.erpoticastec.backenderp.repository.UsuarioRepository;
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
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FuncaoRepository funcaoRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body){
        Usuario usuario = this.usuarioRepository.findByEmail(body.email()).orElseThrow(() -> new InvalidCredentialsException("E-mail ou senha incorretos. Verifique suas credenciais, e tente novamente."));

        if (!passwordEncoder.matches(body.password(), usuario.getPassword())) {
            throw new InvalidCredentialsException("E-mail ou senha incorretos. Verifique suas credenciais, e tente novamente.");
        }

        String token = this.tokenService.generateToken(usuario.getEmail());
        List<Long> oticaIds = usuarioRepository.findOticaIdsByUsuario(usuario.getId());
        return ResponseEntity.ok(new ResponseDTO(usuario.getEmail(), token, usuario.getId(), oticaIds));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
        if (Boolean.TRUE.equals(usuarioRepository.existsByEmail(body.email()))) {
            return ResponseEntity.badRequest().body("Usuario ja cadastrado!");
        }

        Usuario newUsuario = usuarioService.criarUsuario(body);
        String token = tokenService.generateToken(newUsuario.getEmail());

        return ResponseEntity.ok(new ResponseRegisterDTO(newUsuario.getEmail(), token));
    }
}
