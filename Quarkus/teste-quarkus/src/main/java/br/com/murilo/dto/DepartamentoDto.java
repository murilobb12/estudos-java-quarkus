package br.com.murilo.dto;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotBlank;

@Data
@ApplicationScoped
public class DepartamentoDto {

    @NotBlank
    private String nome;

}
