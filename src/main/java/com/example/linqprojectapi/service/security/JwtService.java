package com.example.linqprojectapi.service.security;

import com.example.linqprojectapi.LinqprojectApplication;
import com.example.linqprojectapi.enums.PerfilEnum;
import com.example.linqprojectapi.model.Pessoa;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {
    @Value("${security.jwt.expiracao}")
    private String expiracao;
    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;
    public String gerarToken(Pessoa pessoa) {
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", pessoa.getId());
        claims.put("email", pessoa.getEmail());
        claims.put("perfil", pessoa.getPerfil());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(data)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(chaveAssinatura);
        return Keys.hmacShaKeyFor(key);
    }
    public boolean validarToken(String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataexpiracao = claims.getExpiration();
            LocalDateTime data = dataexpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        } catch (Exception ex) {
            return false;
        }
    }
    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        Claims c = obterClaims(token);
        return (String) c.get("email");
    }

    public static void main(String[] args){
        ConfigurableApplicationContext contexto = SpringApplication.run(LinqprojectApplication.class);
        JwtService service = contexto.getBean(JwtService.class);
        PasswordEncoder passwordEncoder = contexto.getBean(PasswordEncoder.class);
        Pessoa pessoa = new Pessoa("Felio", "felio@email.com", "123", PerfilEnum.ALUNO);
        String token = service.gerarToken(pessoa);
        System.out.println(token);
        boolean isValid = service.validarToken(token);
        System.out.println("Token válido? " + isValid);
        System.out.println("Usuário: " + service.obterLoginUsuario(token));
    }

}