package com.example.linqprojectapi.service;

import com.example.linqprojectapi.dto.ProjetoDTO;
import com.example.linqprojectapi.enums.StatusEnum;
import com.example.linqprojectapi.exception.CustomNotFoundException;
import com.example.linqprojectapi.model.Aluno;
import com.example.linqprojectapi.model.Contato;
import com.example.linqprojectapi.model.Projeto;
import com.example.linqprojectapi.repository.ProjetoRepository;
import jakarta.transaction.Transactional;
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

    public Projeto findById(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);
        return projeto.orElseThrow(() -> new CustomNotFoundException("Projeto com id " + id + " não encontrado."));
    }

    public List<ProjetoDTO> obterTodosProjetos() {
        return projetoRepository.findAll().stream().map((Projeto p) ->
            ProjetoDTO.builder()
                    .id(p.getId())
                    .nome(p.getNome())
                    .descricao(p.getDescricao())
                    .foto(p.getFoto())
                    .video(p.getVideo())
                    .documentacao(p.getDocumentacao())
                    .alunoId(p.getAluno().getId())
                    .status(p.getStatus())
                    .build()).toList();
    }

    @Transactional
    public Projeto cadastrarProjeto(ProjetoDTO projetoDTO) {
        Aluno aluno = alunoService.findById(projetoDTO.getAlunoId());
        return projetoRepository.save(
            Projeto.builder()
            .nome(projetoDTO.getNome())
            .descricao(projetoDTO.getDescricao())
            .foto(projetoDTO.getFoto())
            .video(projetoDTO.getVideo())
            .documentacao(projetoDTO.getDocumentacao())
            .aluno(aluno)
            .status(projetoDTO.getStatus() == null ? StatusEnum.INICIADO : projetoDTO.getStatus())
            .build()
        );
    }

    @Transactional
    public Projeto atualizarProjeto(Long id, ProjetoDTO projetoDTO) {
        Projeto projetoUpdt = findById(id);
        projetoUpdt.setNome(projetoDTO.getNome());
        projetoUpdt.setDescricao(projetoDTO.getDescricao());
        projetoUpdt.setFoto(projetoDTO.getFoto());
        projetoUpdt.setDocumentacao(projetoDTO.getDocumentacao());
        projetoUpdt.setVideo(projetoDTO.getVideo());
        projetoUpdt.setStatus(projetoDTO.getStatus());
        return projetoRepository.save(projetoUpdt);
    }

    @Transactional
    public void deletarProjeto(Long id) {
        Projeto projeto = findById(id);
        projetoRepository.delete(projeto);
    }

    public ProjetoDTO obterProjetoPorId(Long id) {
        Projeto projeto = findById(id);
        return ProjetoDTO.builder()
                .id(projeto.getId())
                .nome(projeto.getNome())
                .descricao(projeto.getDescricao())
                .documentacao(projeto.getDocumentacao())
                .foto(projeto.getFoto())
                .video(projeto.getVideo())
                // .contatos() implementar contatos
                .status(projeto.getStatus())
                .alunoId(projeto.getAluno().getId())
                .build();
    }
}
