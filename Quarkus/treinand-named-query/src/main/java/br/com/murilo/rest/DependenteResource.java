package br.com.murilo.rest;


import br.com.murilo.service.DependenteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("dependentes")
@ApplicationScoped
public class DependenteResource {

    @Inject
    DependenteService dependenteService;

    @GET
    public Response listarDependentes(){
        return Response.status(Response.Status.OK.getStatusCode()).entity(dependenteService.listarDependentes()).build();
    }

    @GET
    @Path("/{id}")
    public Response listarDependentesById(@PathParam("id")Long id){
        return Response.status(Response.Status.OK.getStatusCode()).entity(dependenteService.listarDependentesById(id)).build();
    }




}
