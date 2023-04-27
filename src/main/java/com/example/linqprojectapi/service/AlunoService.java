package com.example.linqprojectapi.service;

import com.example.linqprojectapi.exception.AlunoNotFound;
import com.example.linqprojectapi.model.Aluno;
import com.example.linqprojectapi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno obterPorId(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        System.out.println(aluno);
        return aluno.orElseThrow(() -> new AlunoNotFound("Aluno com id " + id + " não encontrado."));
    }

}
