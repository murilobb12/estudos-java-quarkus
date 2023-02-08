package br.com.murilo.model;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Ordem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double preco;

    @NotNull
    private TipoEnum tipo;

    @NotNull
    private LocalDateTime data;

    @NotEmpty
    private String status;

    @Column(name = "user_id")
    @NotNull
    private Long userId; //Testar usar o tipo Usuario

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.data = datetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {

        return id;
    }

    public Double getPreco() {
        return preco;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public Long getUserId() {
        return userId;
    }
}
