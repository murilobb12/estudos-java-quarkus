package br.com.proxy;

import br.com.model.Cliente;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RegisterRestClient(configKey = "restclient-localhost")
@Produces(MediaType.APPLICATION_JSON)
public interface LocalHostProxy {


    @GET
    @Path("/clientes")
    Cliente listarClientes();


}
