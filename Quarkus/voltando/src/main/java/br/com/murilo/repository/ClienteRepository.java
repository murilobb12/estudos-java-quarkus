package br.com.murilo.repository;

import br.com.murilo.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {



    public List<Cliente> listarClientes(){
        TypedQuery<Cliente> listar_usuarios = getEntityManager().createNamedQuery("LISTAR_USUARIOS", Cliente.class);
        return listar_usuarios.getResultList();
    }

    public int atualizarCliente(Cliente cliente){
        Query query = getEntityManager().createQuery("SELECT * FROM CLIENTE");
        return query.executeUpdate();
    }

    public int inserirCliente(Cliente cliente){
        Query inserir_usuario = getEntityManager().createNamedQuery("INSERIR_USUARIO").setParameter(2, cliente.getIdade()).setParameter(1, cliente.getNome());
        return inserir_usuario.executeUpdate();
    }

    public Cliente listarClienteId(Long id){

        Optional<Cliente> byIdOptional = findByIdOptional(id);
        byIdOptional.orElseThrow(NotFoundException::new);

        TypedQuery<Cliente> listar_usuario_id = getEntityManager().createNamedQuery("LISTAR_USUARIO_ID", Cliente.class).setParameter(1, id);
        return listar_usuario_id.getSingleResult();
    }


    public int deletarClienteId(Long id){
        Optional<Cliente> byIdOptional = findByIdOptional(id);
        byIdOptional.orElseThrow(NotFoundException::new);

        Query query = getEntityManager().createNamedQuery("DELETAR_USUARIO_ID").setParameter(1, id);
        return query.executeUpdate();
    }


}
