package com.example.linqprojectapi.enums;

public enum PerfilEnum {
    ALUNO("aluno"), COLABORADOR("colaborador"), EMPRESA("empresa");

    private String perfil;

    PerfilEnum(String perfil) {
        this.perfil = perfil;
    }
}
