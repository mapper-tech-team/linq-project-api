package com.example.linqprojectapi.repository;

import com.example.linqprojectapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findByEmail(String email);

}
