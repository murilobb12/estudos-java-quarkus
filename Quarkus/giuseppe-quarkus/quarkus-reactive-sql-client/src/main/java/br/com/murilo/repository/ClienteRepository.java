package br.com.murilo.repository;

import br.com.murilo.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {


    public List<Cliente> listarClientes() {

        return getEntityManager().createNamedQuery("LISTAR_CLIENTE", Cliente.class).getResultList();

    }

    public int deletarCliente(Long id) {
        int deletarCliente = getEntityManager().createNamedQuery("DELETAR_CLIENTE").setParameter(1, id).executeUpdate();

        if (deletarCliente == 1) {
            return deletarCliente;
        }
        throw new NotFoundException();

    }

}
