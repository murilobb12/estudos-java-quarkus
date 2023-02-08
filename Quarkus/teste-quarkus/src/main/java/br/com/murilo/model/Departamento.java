package br.com.murilo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Departamento")
@Data
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Departamento(String nome) {
        this.nome = nome;
    }

    public Departamento() {
    }


}
