package com.example.linqprojectapi.controller;


import com.example.linqprojectapi.dto.ColaboradorDTO;
import com.example.linqprojectapi.dto.EmpresaDTO;
import com.example.linqprojectapi.model.Colaborador;
import com.example.linqprojectapi.model.Empresa;
import com.example.linqprojectapi.service.ColaboradorService;
import com.example.linqprojectapi.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/obter")
    public List<EmpresaDTO> obterTodasEmpresas() {
        return empresaService.obterTodasEmpresas();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa cadastrarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        return empresaService.cadastrarEmpresa(empresaDTO);

    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerEmpresa(@PathVariable("id") Long id) {
        empresaService.removerEmpresa(id);
    }

    @GetMapping("/obter/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmpresaDTO obterEmpresaPorId(@PathVariable("id") Long id) {
        return empresaService.obterEmpresaPorId(id);
    }
}
