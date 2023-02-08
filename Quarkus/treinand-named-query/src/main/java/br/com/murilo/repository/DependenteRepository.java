package br.com.murilo.repository;

import br.com.murilo.model.Dependentes;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class DependenteRepository implements PanacheRepository<Dependentes> {


    public List<Dependentes> listarDependentes(){
        Query listar_dependentes = getEntityManager().createNamedQuery("LISTAR_DEPENDENTES");

        List resultList = listar_dependentes.getResultList();

        return resultList;

    }


    public List<Dependentes> listarDependentesById(Long id) {
        Query listar_dependentes_id = getEntityManager().createNamedQuery("LISTAR_DEPENDENTES_ID").setParameter(1, id);

        List resultList = listar_dependentes_id.getResultList();

        return resultList;
    }
}
