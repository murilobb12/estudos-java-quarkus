package br.com.murilo.service;

import br.com.murilo.model.Cliente;
import br.com.murilo.repository.ClienteRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.PathParam;
import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    EntityManager entityManager;

//    public Cliente listarClienteById(Long id){
//
//        Cliente cliente = clienteRepository.findById(id);
//
//        System.out.println(cliente);
//
//        return cliente;
//
//    }

    public List<Cliente> listClientsByName(String nome){

        return clienteRepository.findByName(nome);
    }

    public List<Cliente> listClientsByNameOrderByIdAsc(String nome){

        return clienteRepository.findByNameOrderByIdAsc(nome);

    }

    public List<Cliente> listClientsByNameOrderByIdDesc(String nome){

        return clienteRepository.findByNameOrderByIdDesc(nome);

    }

    public List<Cliente> listarClientes(){

        String jpql = "select c from Cliente c";
        TypedQuery<Cliente> query = clienteRepository
                .getEntityManager()
                .createQuery(jpql, Cliente.class);

        return query.getResultList();

    }

    public List<Cliente> listarClienteById(Long id){

        String jpql = "select c from Cliente c where c.id = :id";

        TypedQuery<Cliente> query = clienteRepository.getEntityManager().createQuery(jpql, Cliente.class).setParameter("id", id);

        return query.getResultList();

    }


    public Cliente salvarCliente(Cliente cliente){

        clienteRepository.persist(cliente);

        return cliente;

    }

    @Inject
    EntityManager em;

    public Object FindByName(){
//        Query namedQuery = em.createNamedQuery("Cliente.findByName");
//        List resultList = namedQuery.getResultList();

        Query nativeQuery = em.createNativeQuery("Cliente.findByName2");

        Object singleResult = nativeQuery.getSingleResult();

        return singleResult;
    }





}
