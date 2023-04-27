package com.example.linqprojectapi.service;

import com.example.linqprojectapi.dto.ProjetoDTO;
import com.example.linqprojectapi.enums.StatusEnum;
import com.example.linqprojectapi.model.Aluno;
import com.example.linqprojectapi.model.Contato;
import com.example.linqprojectapi.model.Projeto;
import com.example.linqprojectapi.repository.ProjetoRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    AlunoService alunoService;

    public List<Projeto> obterTodosProjetos() {
        return projetoRepository.findAll();
    }

    public Projeto obterPorId(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);
        return projeto.orElseThrow(() -> new RuntimeException());
    }

    public Projeto cadastrarProjeto(ProjetoDTO projeto) {
        Aluno aluno = alunoService.obterPorId(projeto.getAlunoId());
        return projetoRepository.save(
            Projeto.builder()
            .nome(projeto.getNome())
            .descricao(projeto.getDescricao())
            .foto(projeto.getFoto())
            .video(projeto.getVideo())
            .documentacao(projeto.getDocumentacao())
            .contatos(projeto.getContatos().stream().map(contato -> Contato.builder()
                .descContato(contato.getDescContato())
                .tipoContato(contato.getTipoContato())
                .build()).collect(Collectors.toList()))
            .aluno(aluno)
            .status(projeto.getStatus() == null ? StatusEnum.INICIADO : projeto.getStatus())
            .build()
        );
    }

    public Projeto atualizarProjeto(Long id, Projeto projeto) {
        Projeto projetoUpdt = obterPorId(id);
        projetoUpdt.setNome(projeto.getNome());
        projetoUpdt.setDescricao(projeto.getDescricao());
        projetoUpdt.setContatos(projeto.getContatos());
        projetoUpdt.setFoto(projeto.getFoto());
        projetoUpdt.setDocumentacao(projeto.getDocumentacao());
        projetoUpdt.setVideo(projeto.getVideo());
        return projetoRepository.save(projetoUpdt);
    }

    public void deletarProjeto(Long id) {
        Projeto projeto = obterPorId(id);
        projetoRepository.delete(projeto);
    }

}
