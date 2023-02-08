package br.com.murilo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Bitcoin {


    private Long id;

    private Double preco;

    private String tipo;

    private LocalDate data;

}
