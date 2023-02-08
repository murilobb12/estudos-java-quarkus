package br.com.murilo.quarkus.rest;

import br.com.murilo.quarkus.models.Fruta;
import br.com.murilo.quarkus.service.FrutaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/frutas")
public class FrutasResource {

    @Inject
    FrutaService frutaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruta> hello() {

        return frutaService.listFrutas();
    }

    @POST
    public void inserirFruta(){
        frutaService.insertFrutas();

    }
}