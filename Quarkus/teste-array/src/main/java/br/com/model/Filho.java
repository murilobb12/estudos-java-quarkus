package br.com.model;

import lombok.Data;

@Data
public class Filho {

    public String nome;

    public int idade;

    public Filho(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}
