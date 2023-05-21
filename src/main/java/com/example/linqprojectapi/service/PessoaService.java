package com.example.linqprojectapi.service;

import com.example.linqprojectapi.dto.PessoaDTO;
import com.example.linqprojectapi.enums.PerfilEnum;
import com.example.linqprojectapi.exception.CustomNotFoundException;
import com.example.linqprojectapi.exception.LoginException;
import com.example.linqprojectapi.model.Pessoa;
import com.example.linqprojectapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService implements UserDetailsService {

    private final PessoaRepository pessoaRepository;
    private final PasswordEncoder passwordEncoder;

    Pessoa findById(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() ->
                new CustomNotFoundException("Pessoa com id " + id + " não encontrada."));
    }

    public Pessoa findByEmail(String email) {
        return pessoaRepository.findByEmail(email);
    }

    PessoaDTO obterPessoaPorId(Long id) {
        Pessoa pessoa = findById(id);
        return PessoaDTO.builder()
                .email(pessoa.getEmail())
                .perfil(pessoa.getPerfil())
                .build();
    }

    List<PessoaDTO> obterPessoas() {
        return pessoaRepository.findAll().stream().map((Pessoa p) ->
            PessoaDTO.builder()
                    .email(p.getEmail())
                    .perfil(p.getPerfil())
                    .build())
                    .toList();
    }

    public UserDetails autenticar(Pessoa pessoa) {
        UserDetails pessoaDetails = loadUserByUsername(pessoa.getEmail());
        boolean senhaOK = passwordEncoder.matches(pessoa.getSenha(),
                pessoaDetails.getPassword());
        if (senhaOK) {
            return pessoaDetails;
        }
        throw new LoginException("Senha inválida");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaRepository.findByEmail(username);
        String[] roles = pessoa.getPerfil().equals(PerfilEnum.ALUNO)
                || pessoa.getPerfil().equals(PerfilEnum.COLABORADOR)
                ? new String[] { "ADMIN", "USER" }
                : new String[] { "USER" };
        return User.builder()
                .username(pessoa.getEmail())
                .password(new BCryptPasswordEncoder().encode(pessoa.getPassword()))
                .roles(roles)
                .build();
    }
}
