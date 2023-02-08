package br.com.murilo.restClient;


import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("solicitacoes")
@RegisterRestClient(configKey = "api-bndes")
public class BndesProxy {

    @GET
    @Path("operacao/credito-livre/{idOperacaoAgente}")
    public


}
