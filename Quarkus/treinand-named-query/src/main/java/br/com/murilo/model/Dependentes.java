package br.com.murilo.model;


import jdk.jfr.Name;
import lombok.Data;

import javax.persistence.*;

@NamedNativeQueries({
                @NamedNativeQuery(name = "LISTAR_DEPENDENTES", query = "SELECT * FROM Dependentes d", resultClass = Dependentes.class),
                @NamedNativeQuery(name = "LISTAR_DEPENDENTES_ID", query = "SELECT * FROM Dependentes d WHERE d.id_responsavel = ?1", resultClass = Dependentes.class)
        })
@Entity(name = "Dependentes")
@Data
@Table(name = "Dependentes")
public class Dependentes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer idade;

    @Column(name = "tipo_dependente")
    private String tipoDependente;

    @ManyToOne
    @JoinColumn(name = "id_responsavel", referencedColumnName = "id")
    private Cliente idResponsavel;

}
