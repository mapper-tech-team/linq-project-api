package com.example.linqprojectapi.controller;

import com.example.linqprojectapi.model.Aluno;
import com.example.linqprojectapi.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/cadastrarAluno")
    public Aluno cadastrarAluno(@RequestBody Aluno aluno) {
        return alunoService.cadastrarAluno(aluno);
    }

}
