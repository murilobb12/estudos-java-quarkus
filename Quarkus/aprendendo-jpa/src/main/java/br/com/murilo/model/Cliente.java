package br.com.murilo.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedQuery(name =  "Cliente.findByName", query = "SELECT c FROM Cliente c WHERE c.nome = 'Murilo'")
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

}
