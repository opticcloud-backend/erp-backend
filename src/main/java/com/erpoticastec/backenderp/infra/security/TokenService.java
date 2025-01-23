package com.erpoticastec.backenderp.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.erpoticastec.backenderp.service.UserService;
import com.erpoticastec.backenderp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TokenService {
    @Value("${app.jwtSecret}")
    private String secret;

    @Autowired
    private UserService userService;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("login-auth-api-back-end-opticcloud-tec")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while authenticating");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api-back-end-opticcloud-tec")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

    public List<Integer> getFazendasFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Verifica e decodifica o token
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("login-auth-api-back-end-fmt-tec")
                    .build()
                    .verify(token);

            List<Integer> fazendas = decodedJWT.getClaim("fazendas").asList(Integer.class);

            return fazendas;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token");
        }
    }
}
