package com.example.linqprojectapi.controller;

import com.example.linqprojectapi.dto.AlunoDTO;
import com.example.linqprojectapi.model.Aluno;
import com.example.linqprojectapi.service.AlunoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/aluno")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/obter")
    public List<AlunoDTO> obterTodosAlunos() {
        return alunoService.obterTodosAlunos();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
        return alunoService.cadastrarAluno(alunoDTO);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerAluno(@PathVariable("id") Long id) {
        alunoService.removerAluno(id);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Aluno atualizarAluno(@PathVariable("id") Long id, @RequestBody AlunoDTO alunoDTO) {
        return alunoService.atualizarAluno(id, alunoDTO);
    }

    @GetMapping("/obter/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlunoDTO obterAlunoPorId(@PathVariable("id") Long id) {
        return alunoService.obterAlunoPorId(id);
    }

}
