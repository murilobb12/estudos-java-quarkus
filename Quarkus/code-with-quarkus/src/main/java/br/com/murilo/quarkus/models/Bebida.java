package br.com.murilo.quarkus.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Bebida extends PanacheEntity {

    public String nome;

    public int quantidade;

}