package com.example.linqprojectapi.service;

import com.example.linqprojectapi.model.Projeto;
import com.example.linqprojectapi.repository.ProjetoRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    ProjetoRepository projetoRepository;

    public List<Projeto> obterTodosProjetos() {
        return projetoRepository.findAll();
    }

    public Projeto findById(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);
        return projeto.orElseThrow(() -> new RuntimeException());
    }

    public Projeto cadastrarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public Projeto atualizarProjeto(Long id, Projeto projeto) {
        Projeto projetoUpdt = findById(id);
        projetoUpdt.setNome(projeto.getNome());
        projetoUpdt.setDescricao(projeto.getDescricao());
        projetoUpdt.setContatos(projeto.getContatos());
        projetoUpdt.setFoto(projeto.getFoto());
        projetoUpdt.setDocumentacao(projeto.getDocumentacao());
        projetoUpdt.setVideo(projeto.getVideo());
        return projetoRepository.save(projetoUpdt);
    }

    public void deletarProjeto(Long id) {
        Projeto projeto = findById(id);
        projetoRepository.delete(projeto);
    }

}
