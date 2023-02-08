package br.com.murilo.quarkus;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Teste extends PanacheEntity {

    String teste;

}
