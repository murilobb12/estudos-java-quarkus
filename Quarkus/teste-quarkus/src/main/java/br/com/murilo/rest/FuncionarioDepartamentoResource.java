package br.com.murilo.rest;

import br.com.murilo.service.DepartamentoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Consumes
@Produces
@Path("/departamentos/{id}/funcionarios")
public class FuncionarioDepartamentoResource {

    @Inject
    DepartamentoService departamentoService;

    @GET
    public Response funcionariosPorDepartamento(@PathParam("id")Long id){

        return Response.status(Response.Status.OK.getStatusCode()).entity(departamentoService.funcionariosPorDepartamento(id)).build();

    }

}
