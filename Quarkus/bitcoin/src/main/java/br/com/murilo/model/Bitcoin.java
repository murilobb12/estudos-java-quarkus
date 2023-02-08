package br.com.murilo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@RequestScoped
@Entity
public class Bitcoin extends PanacheEntity {

    @NotNull
    private Double preco;

    @NotNull
    private TipoEnum tipo;

    @NotNull
    private LocalDate data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
