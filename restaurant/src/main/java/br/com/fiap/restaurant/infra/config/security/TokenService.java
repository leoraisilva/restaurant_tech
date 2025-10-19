package br.com.fiap.restaurant.infra.config.security;

import br.com.fiap.restaurant.infra.adapter.outbound.persistence.entity.usuario.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String GenerationToken(UsuarioEntity usuarioEntity) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("restaurant")
                    .withSubject(usuarioEntity.getUsername())
                    .withExpiresAt(LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new JWTCreationException("Error while Generate Token", e);
        }
    }

    public String validationToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("restaurant")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Error Validate on token", e);
        }
    }

}
