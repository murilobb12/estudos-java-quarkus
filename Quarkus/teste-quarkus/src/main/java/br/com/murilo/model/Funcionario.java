package br.com.murilo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedNativeQuery(name = "teste", query = "SELECT a.id, a.nome, a.idade, a.dep_id FROM Funcionario a")
@Entity(name = "Funcionario")
@Table(name = "Funcionario")
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nome;

    @Column(name = "idade")
    private Integer idade;

    @NotNull(message = "Funcionário deve estar vinculado a um Departamento!")
    @ManyToOne
    @JoinColumn(name = "dep_id", referencedColumnName = "id")
    private Departamento departamentoId;




}
