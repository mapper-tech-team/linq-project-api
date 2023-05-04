package com.example.linqprojectapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@PrimaryKeyJoinColumn(name = "colaboradorId")
@Table(name = "colaborador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Colaborador extends Pessoa{

    @Column(nullable = false)
    private String cargo;

}
