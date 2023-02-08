package br.com.murilo.rest;

import br.com.murilo.repository.ClienteRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteRepository clienteRepository;

    @GET
    public Response listarClientes() {
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteRepository.listarClientes()).build();
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public Response deletarCliente(@PathParam("id")Long id){
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteRepository.deletarCliente(id)).build();
    }


}
