package com.example.linqprojectapi.dto;

import com.example.linqprojectapi.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PessoaDTO {

    private String email;
    private String senha;
    private PerfilEnum perfil;

}
