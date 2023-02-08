package br.com.murilo.rest;

import br.com.murilo.model.Cliente;
import br.com.murilo.service.ClienteService;
import org.jboss.logging.annotations.Param;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("clientes")
public class ClienteResource {

    @Inject
    ClienteService clienteService;


    @GET
    public Response listarCLientes(){

        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.listarClientes()).build();

    }

    @Path("/{id}")
    @GET
    public Response listarClienteById (@PathParam("id")Long id){

        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.listarClienteById(id)).build();

    }

    @Path("/nome/{nome}")
    @GET
    public Response listClientsByName(@PathParam("nome")String nome){
        return Response.status(Response.Status.OK).entity(clienteService.listClientsByName(nome)).build();
    }

    @Path("/nomeasc/{nome}")
    @GET
    public Response listClientsByNameOrderByIdAsc(@PathParam("nome")String nome){
        return Response.status(Response.Status.OK).entity(clienteService.listClientsByNameOrderByIdAsc(nome)).build();
    }

    @Path("/nomedesc/{nome}")
    @GET
    public Response listClientsByNameOrderByIdDesc(@PathParam("nome")String nome){
        return Response.status(Response.Status.OK).entity(clienteService.listClientsByNameOrderByIdDesc(nome)).build();
    }

    @Path("/named")
    @GET
    public Response listClientsByNameNamedQuery(){
        return Response.status(Response.Status.OK.getStatusCode()).entity(clienteService.FindByName()).build();
    }

    @POST
    @Transactional
    public Response salvarCliente(Cliente cliente){
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(clienteService.salvarCliente(cliente)).build();
    }





}
