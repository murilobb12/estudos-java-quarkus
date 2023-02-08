package br.com.murilo.resource;


import br.com.murilo.model.Ordem;
import br.com.murilo.repository.OrdemRepository;
import br.com.murilo.service.OrdemService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDateTime;
import java.util.List;

@Path("/ordens")
public class OrdemResource {

    @Inject
    OrdemService ordemService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ordem> listarOrdens(){
        return ordemService.listAll();
    }

    @POST
    @RolesAllowed("user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Ordem inserirOrdem(@Context SecurityContext securityContext, Ordem ordem){
        System.out.println(ordem.getTipo().ordinal());
        ordemService.inserirOrdem(securityContext, ordem);

        return ordem;
    }

}
