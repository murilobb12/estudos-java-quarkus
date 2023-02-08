package br.com.murilo.model;

import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;

@RequestScoped
@Entity
@Data
@Table(name = "USERS")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "age")
    private Integer age;
    @Column(name = "name")
    private String name;

}
