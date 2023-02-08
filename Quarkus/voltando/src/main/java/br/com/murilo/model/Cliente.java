package br.com.murilo.model;


import jdk.jfr.Name;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "Cliente")
@Table(name = "Cliente")
@Data
@NamedNativeQueries({
        @NamedNativeQuery(name = "LISTAR_USUARIOS", query = "SELECT * FROM CLIENTE c", resultClass = Cliente.class),
        @NamedNativeQuery(name = "INSERIR_USUARIO", query = "INSERT INTO CLIENTE(nome,idade) VALUES (?1, ?2)"),
        @NamedNativeQuery(name = "LISTAR_USUARIO_ID", query = "SELECT * FROM CLIENTE WHERE id = ?1", resultClass = Cliente.class),
        @NamedNativeQuery(name = "DELETAR_USUARIO_ID", query = "DELETE FROM CLIENTE WHERE id = ?1")
})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer idade;


}
