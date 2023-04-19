package com.example.linqprojectapi.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.linqprojectapi.model.Pessoa;
import com.auth0.jwt.JWT;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(Pessoa pessoa) {
        return JWT.create()
                .withIssuer("Projetos")
                .withSubject(pessoa.getEmail())
                .withClaim("id", pessoa.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(10)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secreta"));
    }

}
