package br.com.murilo.repository;

import br.com.murilo.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public List<Cliente> findByName(String nome){
        return list("nome", nome);
    }

    public List<Cliente> findByNameOrderByIdAsc(String nome){
        return list("nome", Sort.ascending("id"), nome);
    }

    public List<Cliente> findByNameOrderByIdDesc(String nome){
        return list("nome", Sort.descending("id"), nome);
    }


}