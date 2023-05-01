package com.example.linqprojectapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "alunoId")
@Table(name = "alunos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Aluno extends Pessoa {

    @Column(nullable = false, length = 6)
    private Long ra;

    @OneToMany(mappedBy = "aluno")
    private List<Projeto> projetos;

    @Column(nullable = false)
    private Boolean isAlumni;
}
