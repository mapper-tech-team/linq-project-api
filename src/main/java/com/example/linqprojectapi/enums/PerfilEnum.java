package com.example.linqprojectapi.enums;

import lombok.Getter;

@Getter
public enum PerfilEnum {
    ALUNO("aluno"),
    COLABORADOR("colaborador"),
    EMPRESA("empresa");

    private final String perfil;

    PerfilEnum(String perfil) {
        this.perfil = perfil;
    }
}
