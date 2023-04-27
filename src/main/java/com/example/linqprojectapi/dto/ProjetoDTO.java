package com.example.linqprojectapi.dto;

import com.example.linqprojectapi.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjetoDTO {

    private String nome;

    private String descricao;

    private String foto;

    private String video;

    private String documentacao;

    private Long alunoId;

    private StatusEnum status;

    private List<ContatoDTO> contatos;

}
