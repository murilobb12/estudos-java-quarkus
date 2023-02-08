package br.com.murilo.quarkus.service;

import br.com.murilo.quarkus.models.Bebida;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BebidaService {

    public List<Bebida> listBebidas(){
        return Bebida.listAll();
    }


    @Transactional
    public void insertBebidas(){
        Bebida bebida = new Bebida();
        bebida.nome = "CocaCola - Zero";
        bebida.quantidade = 10;
        bebida.persist();
    }

}
