package com.example.linqprojectapi.controller;

import com.example.linqprojectapi.model.Projeto;
import com.example.linqprojectapi.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjetoController {

    @Autowired
    ProjetoService projetoService;

    @GetMapping("/obterTodos")
    public List<Projeto> obterTodosProjetos() {
        return projetoService.obterTodosProjetos();
    }

    @PostMapping("/cadastrar")
    public Projeto cadastrarProjeto(@RequestBody Projeto projeto) {
        return projetoService.cadastrarProjeto(projeto);
    }

    @PutMapping("/atualizar/{id}")
    public Projeto atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
        return projetoService.atualizarProjeto(id, projeto);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarProjeto(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
    }

}
