package com.example.linqprojectapi.service;

import com.example.linqprojectapi.dto.ColaboradorDTO;
import com.example.linqprojectapi.dto.EmpresaDTO;
import com.example.linqprojectapi.enums.PerfilEnum;
import com.example.linqprojectapi.exception.CustomNotFoundException;
import com.example.linqprojectapi.model.Colaborador;
import com.example.linqprojectapi.model.Empresa;
import com.example.linqprojectapi.repository.ColaboradorRepository;
import com.example.linqprojectapi.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public Empresa cadastrarEmpresa(EmpresaDTO empresaDTO) {
        return empresaRepository.save(Empresa.builder()
                .nome(empresaDTO.getNome())
                .email(empresaDTO.getEmail())
                .senha(empresaDTO.getSenha())
                .cnpj(empresaDTO.getCnpj())
                .perfil(PerfilEnum.EMPRESA)
                .build());
    }

    public List<EmpresaDTO> obterTodasEmpresas() {
        return empresaRepository.findAll().stream().map((Empresa a) ->
                        EmpresaDTO.builder()
                                .id(a.getId())
                                .nome(a.getNome())
                                .email(a.getEmail())
                                .cnpj(a.getCnpj())
                                .build())
                .toList();
    }

    public Empresa findById(Long id) {
        return empresaRepository.findById(id).orElseThrow(() ->
                new CustomNotFoundException("Empresa com id " + id + " não encontrado."));
    }

    @Transactional
    public void removerEmpresa(Long id) {
        Empresa empresa = findById(id);
        empresaRepository.delete(empresa);
    }

    public EmpresaDTO obterEmpresaPorId(Long id) {
        Empresa empresa = findById(id);
        return EmpresaDTO.builder()
                .id(empresa.getId())
                .nome(empresa.getNome())
                .email(empresa.getEmail())
                .cnpj(empresa.getCnpj())
                .build();
    }

    @Transactional
    public Empresa atualizarEmpresa(Long id, EmpresaDTO empresaDTO) {
        Empresa empresa = findById(id);
        empresa.setNome(empresaDTO.getNome());
        empresa.setEmail(empresaDTO.getEmail());
        empresa.setSenha(empresaDTO.getSenha());
        empresa.setCnpj(empresaDTO.getCnpj());
        return empresa;
    }
}
