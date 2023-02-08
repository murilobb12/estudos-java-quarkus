package br.com.murilo.controller;


import br.com.murilo.dto.ClienteDto;
import br.com.murilo.model.Cliente;
import br.com.murilo.service.ClienteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    ClienteService clienteService;


    @GET
    public Response listCliente(){

        List<Cliente> clientes = clienteService.listCliente();

        return Response.ok(clientes).status(200).build();

    }

    @GET
    @Path("{id}")
    public Response ClientById(@PathParam("id")Long id){

        Cliente cliente = clienteService.clienteById(id);

        return Response.ok(cliente).status(200).build();


    }



    @POST
    public Response saveCliente(ClienteDto dto){

        Cliente cliente = clienteService.saveCliente(dto);

        return Response.ok(cliente).status(201).build();

    }

    @PUT
    @Path("{id}")
    public Response updateCliente(@PathParam("id")Long id, ClienteDto dto){

        clienteService.updateCliente(id, dto);

        return Response.status(204).build();

    }

    @DELETE
    @Path("{id}")
    public Response deleteCliente(@PathParam("id")Long id){

        clienteService.deleteCliente(id);

        return Response.status(204).build();

    }


}
