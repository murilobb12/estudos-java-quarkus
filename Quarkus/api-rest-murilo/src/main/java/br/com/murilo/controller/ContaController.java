package br.com.murilo.controller;

import br.com.murilo.dto.ContaDto;
import br.com.murilo.model.Conta;
import br.com.murilo.service.ContaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Path("contas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContaController {


    @Inject
    ContaService contaService;


    @GET
    public Response listContas(){

        List<Conta> contas = contaService.listContas();

        return Response.ok(contas).status(Response.Status.OK).build();

    }


    @GET
    @Path("{id}")
    public Response ListContasById(@PathParam("id")Long id){

        Conta conta = contaService.listContasById(id);

        return Response.ok(conta).status(201).build();

    }

    @POST
    public Response saveConta(ContaDto dto){

        Conta conta = contaService.saveContas(dto);

        return Response.ok(conta).status(Response.Status.OK).build();
    }

    @PUT
    @Path("{id}")
    public Response updateConta(@PathParam("id")Long id, ContaDto dto){

        contaService.updateConta(id, dto);

        return Response.ok().status(204).build();

    }


    @DELETE
    @Path("{id}")
    public Response deleteConta(@PathParam("id")Long id){

        contaService.deleteConta(id);

        return Response.ok().status(204).build();

    }


    @PUT
    @Path("sacar/{id}/{valor}")
    public Response sacarSaldo(@PathParam("id")Long id, @PathParam("valor") BigDecimal valor){

        contaService.sacarSaldo(id, valor);

        return Response.ok().status(204).build();


    }

    @PUT
    @Path("depositar/{id}/{valor}")
    public Response depositarSaldo(@PathParam("id")Long id, @PathParam("valor")BigDecimal valor){

        contaService.depositarSaldo(id, valor);

        return Response.ok().status(204).build();

    }




}
