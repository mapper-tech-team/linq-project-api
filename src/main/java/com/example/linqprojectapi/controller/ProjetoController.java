package com.example.linqprojectapi.controller;

import com.example.linqprojectapi.dto.ProjetoDTO;
import com.example.linqprojectapi.model.Projeto;
import com.example.linqprojectapi.service.ProjetoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projeto")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class ProjetoController {

    @Autowired
    ProjetoService projetoService;

    @GetMapping("/obter")
    public List<ProjetoDTO> obterTodosProjetos() {
        return projetoService.obterTodosProjetos();
    }

    @GetMapping("/obter/{id}")
    public ProjetoDTO obterProjetoPorId(@PathVariable("id") Long id) {
        return projetoService.obterProjetoPorId(id);
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto cadastrarProjeto(@RequestBody ProjetoDTO projetoDTO) {
        return projetoService.cadastrarProjeto(projetoDTO);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Projeto atualizarProjeto(@PathVariable Long id, @RequestBody ProjetoDTO projetoDTO) {
        return projetoService.atualizarProjeto(id, projetoDTO);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProjeto(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
    }

}
