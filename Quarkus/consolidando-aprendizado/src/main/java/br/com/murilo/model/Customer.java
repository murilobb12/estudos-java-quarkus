package br.com.murilo.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String age;

    private String email;

}
