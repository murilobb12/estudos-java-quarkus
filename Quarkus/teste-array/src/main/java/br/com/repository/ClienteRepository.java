package br.com.repository;


import br.com.model.Cliente;
import br.com.model.Entrada;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Entrada> {




    public List<Entrada> listarCLientes(){

        return listAll();

    }




}



