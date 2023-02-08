package br.com.murilo.rest;

import br.com.murilo.service.BndesProxy;
import br.com.murilo.service.GerarToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.IOException;

@RequestScoped
@Path("/png")
public class BndesResource {


    @RestClient
    @Inject
    BndesProxy proxy;
    @Inject
    GerarToken token;


    @Path("/{id}")
    @GET
    public Response listarSolicitacao(@PathParam("id") Long id) throws IOException, InterruptedException {

        proxy.solicitacaoRetorno(token.gerarToken(), id);

        try {
            return Response.status(Response.Status.OK.getStatusCode()).build();
        } catch (Exception e) {
           return Response.status(Response.Status.PROXY_AUTHENTICATION_REQUIRED).entity(e.getMessage()).build();

        }


    }


}
