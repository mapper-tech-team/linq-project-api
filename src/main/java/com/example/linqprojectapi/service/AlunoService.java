package com.example.linqprojectapi.service;

import com.example.linqprojectapi.dto.AlunoDTO;
import com.example.linqprojectapi.exception.CustomNotFoundException;
import com.example.linqprojectapi.model.Aluno;
import com.example.linqprojectapi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno cadastrarAluno(AlunoDTO alunoDTO) {
        return alunoRepository.save(Aluno.builder()
            .nome(alunoDTO.getNome())
            .email(alunoDTO.getEmail())
            .senha(alunoDTO.getSenha())
            .ra(alunoDTO.getRa())
            .isAlumni(alunoDTO.getIsAlumni())
            .build());
    }

    public List<AlunoDTO> obterTodosAlunos() {
        return alunoRepository.findAll().stream().map((Aluno a) ->
            AlunoDTO.builder()
                .id(a.getId())
                .nome(a.getNome())
                .email(a.getEmail())
                .ra(a.getRa())
                .isAlumni(a.getIsAlumni())
                .build())
                .toList();
    }

    public Aluno findById(Long id) {
        return alunoRepository.findById(id).orElseThrow(() ->
                new CustomNotFoundException("Aluno com id " + id + " não encontrado."));
    }

    public void removerAluno(Long id) {
        Aluno aluno = findById(id);
        alunoRepository.delete(aluno);
    }

    public Aluno atualizarAluno(Long id,  AlunoDTO alunoDTO) {
        Aluno aluno = findById(id);
        aluno.setNome(alunoDTO.getNome());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setSenha(alunoDTO.getSenha());
        aluno.setRa(alunoDTO.getRa());
        aluno.setIsAlumni(alunoDTO.getIsAlumni());
        return alunoRepository.save(aluno);
    }

    public AlunoDTO obterAlunoPorId(Long id) {
        Aluno aluno = findById(id);
        return AlunoDTO.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .email(aluno.getEmail())
                .ra(aluno.getRa())
                .isAlumni(aluno.getIsAlumni())
                .build();
    }
}
