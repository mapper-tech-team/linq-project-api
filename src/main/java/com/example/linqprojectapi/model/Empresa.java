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
@PrimaryKeyJoinColumn(name = "EmpresaId")
@Table (name = "empresa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Empresa extends Pessoa{
    @Column(nullable = false, length = 14)
    private String cnpj;
}
