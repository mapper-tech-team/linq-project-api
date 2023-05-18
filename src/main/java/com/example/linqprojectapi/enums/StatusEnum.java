package com.example.linqprojectapi.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    INICIADO("iniciado"),
    EM_ANDAMENTO("em andamento"),
    CONCLUIDO("concluído");

    private final String status;

    StatusEnum(String status) {
        this.status = status;
    }
}
