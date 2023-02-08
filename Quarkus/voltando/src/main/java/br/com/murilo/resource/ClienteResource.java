package br.com.murilo.resource;

import br.com.murilo.model.Cliente;
import br.com.murilo.repository.ClienteRepository;
import br.com.murilo.service.ClienteService;
import org.jboss.logging.annotations.Pos;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/clientes")
public class ClienteResource {

//    @Inject
//    ClienteService clienteService;

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    public ClienteResource(ClienteService clienteService, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    @GET
    public Response listarClientes() {
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.listarClientes()).build();
    }

    @GET
    @Path("/{id}")
    public Response listarClienteId(@PathParam("id") Long id) {
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.listarClienteId(id)).build();
    }


    @POST
    @Transactional
    public Response inserirCliente(Cliente cliente) {
        System.out.println(cliente.getNome());
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.inserirCliente(cliente)).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deletarClienteId(@PathParam("id")Long id){
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.deletarClienteId(id)).build();
    }

}
