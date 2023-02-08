package br.com.murilo.model;

import lombok.*;
import org.hibernate.annotations.ColumnTransformers;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(name = "LISTAR_CLIENTE", query = "SELECT * FROM CLIENTE c", resultClass = Cliente.class),
        @NamedNativeQuery(name = "DELETAR_CLIENTE", query = "DELETE FROM CLIENTE WHERE ID = ?1")
})
@Data
@ApplicationScoped
@Entity(name = "Cliente")
@Table()
public class Cliente {


    @Id
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;



}