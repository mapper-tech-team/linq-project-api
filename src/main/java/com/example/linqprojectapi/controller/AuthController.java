package com.example.linqprojectapi.controller;

import com.example.linqprojectapi.dto.AutenticacaoDTO;
import com.example.linqprojectapi.dto.TokenDTO;
import com.example.linqprojectapi.exception.CustomNotFoundException;
import com.example.linqprojectapi.exception.LoginException;
import com.example.linqprojectapi.model.Pessoa;
import com.example.linqprojectapi.service.PessoaService;
import com.example.linqprojectapi.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private JwtService jwtService;

    @PostMapping()
    public TokenDTO autenticar(@RequestBody AutenticacaoDTO autenticacaoDTO) {
        try {
            Pessoa pessoa = pessoaService.findByEmail(autenticacaoDTO.getEmail());
            UserDetails pessoaAutenticada = pessoaService.autenticar(pessoa);
            String token = jwtService.gerarToken(pessoa);
            return new TokenDTO(pessoa.getEmail(), token);
        } catch(CustomNotFoundException | LoginException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }

}

