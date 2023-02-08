package br.com.model;

import lombok.Data;

import java.util.List;

@Data
public class Cliente {

    public String nome;

    public int idade;

    public List<Filho> filhos;


}
