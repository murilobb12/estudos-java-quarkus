package br.com.murilo.quarkus.rest;

import br.com.murilo.quarkus.models.Bebida;
import br.com.murilo.quarkus.service.BebidaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bebidas")
public class BebidaResource {

    @Inject
    BebidaService bebidaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bebida> bebidaList() {

        return bebidaService.listBebidas();
    }

    @POST
    public void insereBebida(){
        bebidaService.insertBebidas();
    }
}