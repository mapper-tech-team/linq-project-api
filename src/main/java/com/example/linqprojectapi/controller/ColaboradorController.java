package com.example.linqprojectapi.controller;

import com.example.linqprojectapi.dto.AlunoDTO;
import com.example.linqprojectapi.dto.ColaboradorDTO;
import com.example.linqprojectapi.model.Aluno;
import com.example.linqprojectapi.model.Colaborador;
import com.example.linqprojectapi.service.AlunoService;
import com.example.linqprojectapi.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaborador")
@CrossOrigin(origins = "*")
public class ColaboradorController {
    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping("/obter")
    public List<ColaboradorDTO> obterTodosColaborador() {
        return colaboradorService.obterTodosColaborador();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Colaborador cadastrarColaborador(@RequestBody ColaboradorDTO colaboradorDTO) {
        return colaboradorService.cadastrarColaborador(colaboradorDTO);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerColaborador(@PathVariable("id") Long id) {
        colaboradorService.removerColaborador(id);
    }

    @GetMapping("/obter/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColaboradorDTO obterColaboradorPorId(@PathVariable("id") Long id) {
        return colaboradorService.obterColaboradorPorId(id);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Colaborador editarColaborador(@PathVariable("id") Long id, @RequestBody ColaboradorDTO colaboradorDTO) {
        return colaboradorService.atualizarColaborador(id, colaboradorDTO);
    }
}
