package br.com.model;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@Data
public class Entrada {

    public Cliente[] clientes;


}
