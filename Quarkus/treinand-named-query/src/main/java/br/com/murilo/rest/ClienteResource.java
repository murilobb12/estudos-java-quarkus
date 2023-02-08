package br.com.murilo.rest;

import br.com.murilo.model.Cliente;
import br.com.murilo.repository.ClienteRepository;
import br.com.murilo.service.ClienteService;
import io.quarkus.arc.runtime.test.PreloadedTestApplicationClassPredicate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
public class ClienteResource {

    @Inject
    ClienteService clienteService;

    @Inject
    ClienteRepository clienteRepository;


    @Path("/basico")
    @GET
    public Response listarClientes(){
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.clienteListBasic()).build();
    }
    @Path("/named")
    @GET
    public Response listarClientesNamed(){
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.clienteListNamed()).build();
    }

    @Path("/named/{id}")
    @GET
    public Response listarClientesNamedId(@PathParam("id")Long id){
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteRepository.clienteListById(id)).build();
    }

    @Path("/named/{id}")
    @DELETE
    @Transactional
    public Response deletarClienteId(@PathParam("id")Long id){
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.deletarCliente(id)).build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response atualizarCliente(Cliente cliente, @PathParam("id") Long id){

        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.atualizarCliente(cliente,id)).build();

    }

    @POST
    @Transactional
    public Response inserirCliente(List<Cliente> cliente){
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.inserirCliente(cliente)).build();
    }

}
