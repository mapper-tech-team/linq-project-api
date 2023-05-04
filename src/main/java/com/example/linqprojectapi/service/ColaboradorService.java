package com.example.linqprojectapi.service;

import com.example.linqprojectapi.dto.AlunoDTO;
import com.example.linqprojectapi.dto.ColaboradorDTO;
import com.example.linqprojectapi.exception.CustomNotFoundException;
import com.example.linqprojectapi.model.Aluno;
import com.example.linqprojectapi.model.Colaborador;
import com.example.linqprojectapi.repository.ColaboradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Colaborador cadastrarColaborador(ColaboradorDTO colaboradorDTO) {
        return colaboradorRepository.save(Colaborador.builder()
                .nome(colaboradorDTO.getNome())
                .email(colaboradorDTO.getEmail())
                .senha(colaboradorDTO.getSenha())
                .cargo(colaboradorDTO.getCargo())
                .build());
    }

    public List<ColaboradorDTO> obterTodosColaborador() {
        return colaboradorRepository.findAll().stream().map((Colaborador a) ->
                        ColaboradorDTO.builder()
                                .id(a.getId())
                                .nome(a.getNome())
                                .email(a.getEmail())
                                .cargo(a.getCargo())
                                .build())
                .toList();
    }

    public Colaborador findById(Long id) {
        return colaboradorRepository.findById(id).orElseThrow(() ->
                new CustomNotFoundException("Colaborador com id " + id + " não encontrado."));
    }

    @Transactional
    public void removerColaborador(Long id) {
        Colaborador colaborador = findById(id);
        colaboradorRepository.delete(colaborador);
    }


    public ColaboradorDTO obterColaboradorPorId(Long id) {
        Colaborador colaborador = findById(id);
        return ColaboradorDTO.builder()
                .id(colaborador.getId())
                .nome(colaborador.getNome())
                .email(colaborador.getEmail())
                .cargo(colaborador.getCargo())
                .build();
    }
}
