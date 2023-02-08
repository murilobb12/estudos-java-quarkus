package br.com.murilo.quarkus.service;

import br.com.murilo.quarkus.models.Fruta;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class FrutaService {

    public List<Fruta> listFrutas(){
        return Fruta.listAll();
    }


    @Transactional
    public void insertFrutas(){
        Fruta fruta = new Fruta();
        fruta.nome = "Maça";
        fruta.quantidade = 5;
        fruta.persist();
    }

}
