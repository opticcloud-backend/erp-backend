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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

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
        logger.info("Recebida requisição de login para o e-mail: {}", body.email());
        Usuario usuario = this.usuarioRepository.findByEmail(body.email())
                .orElseThrow(() -> {
                    logger.warn("Tentativa de login falhou: usuário com e-mail {} não encontrado", body.email());
                    return new InvalidCredentialsException("E-mail ou senha incorretos. Verifique suas credenciais, e tente novamente.");
                });

        if (!passwordEncoder.matches(body.password(), usuario.getPassword())) {
            logger.warn("Tentativa de login falhou: senha incorreta para o e-mail {}", body.email());
            throw new InvalidCredentialsException("E-mail ou senha incorretos. Verifique suas credenciais, e tente novamente.");
        }

        String token = this.tokenService.generateToken(usuario.getEmail());
        List<Long> oticaIds = usuarioRepository.findOticaIdsByUsuario(usuario.getId());
        logger.info("Login bem-sucedido para o usuário {} (ID: {})", usuario.getEmail(), usuario.getId());
        return ResponseEntity.ok(new ResponseDTO(usuario.getEmail(), token, usuario.getId(), oticaIds));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
        logger.info("Requisição de registro para o e-mail: {}", body.email());

        if (Boolean.TRUE.equals(usuarioRepository.existsByEmail(body.email()))) {
            logger.warn("Tentativa de registro falhou: usuário com e-mail {} já existe", body.email());
            return ResponseEntity.badRequest().body("Usuário já cadastrado!");
        }

        Usuario newUsuario = usuarioService.criarUsuario(body);
        String token = tokenService.generateToken(newUsuario.getEmail());
        logger.info("Usuário registrado com sucesso: {} (ID: {})", newUsuario.getEmail(), newUsuario.getId());

        return ResponseEntity.ok(new ResponseRegisterDTO(newUsuario.getEmail(), token));
    }
}
