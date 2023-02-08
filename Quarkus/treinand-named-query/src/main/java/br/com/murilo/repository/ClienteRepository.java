package br.com.murilo.repository;

import br.com.murilo.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public List<Cliente> clienteListBasic(){
        return listAll();
    }

    public List<Cliente> clienteListNamed(){

        TypedQuery<Cliente> usuarios = getEntityManager().createNamedQuery("CONSULTAR_USUARIO", Cliente.class);

        return usuarios.getResultList();

    }

    public Cliente clienteListById(Long id){

            Optional<Cliente> byIdOptional = findByIdOptional(id);
            byIdOptional.orElseThrow(() -> new NotFoundException("ID não encontrado"));

        TypedQuery<Cliente> usuario_id = getEntityManager().createNamedQuery("CONSULTAR_USUARIO_ID", Cliente.class).setParameter(1,id);

        return usuario_id.getSingleResult();

    }

    public int clienteDeleteById(Long id){

        Query deletar_usuario_id = getEntityManager().createNamedQuery("DELETAR_USUARIO_ID").setParameter(1, id);

        return deletar_usuario_id.executeUpdate();


    }


    public int  atualizarCliente(Cliente cliente, Long id) {

        Query atualizar_usuario = getEntityManager().createNamedQuery("ATUALIZAR_USUARIO").setParameter(1, cliente.getNome()).setParameter(2, id);

        return atualizar_usuario.executeUpdate();

    }

    public int inserirCliente(List<Cliente> cliente){

        List<Cliente> clientes = new ArrayList<>();

        int i1 = 0;

        //Alterar por for Cliente: cliente valor
        for (int i = 0; i < cliente.size(); i++) {
            clientes.add(new Cliente());
            Query query = getEntityManager().createNamedQuery("INSERIR_USUARIO")
                    .setParameter(1, cliente.get(i).getNome())
                    .setParameter(2, cliente.get(i).getIdade());

            System.out.println(i1);

            i1 = query.executeUpdate();
        }

        return i1;

    }
}
