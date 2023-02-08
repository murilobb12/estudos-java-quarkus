package br.com.murilo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FuncionarioIdadeNomeResponse {

    @NotBlank
    private String nome;
    @NotNull
    private Integer idade;

}
