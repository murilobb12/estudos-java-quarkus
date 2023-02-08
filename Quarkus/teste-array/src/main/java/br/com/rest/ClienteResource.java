package br.com.rest;


import br.com.model.Cliente;
import br.com.model.Entrada;
import br.com.repository.ClienteRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
@RequestScoped
public class ClienteResource {

    @Inject
    ClienteRepository clienteRepository;



    @Inject
    Entrada entrada;

    @Path("/entrada")
    @POST
    public Response inserirCliente(Entrada jsonEntrada) {

        System.out.println(jsonEntrada.clientes[1]);

        return Response.status(Response.Status.OK.getStatusCode()).entity(jsonEntrada).build();


    }
    @Path("/array")
    @POST
    public Response inserirCliente( ) {


        System.out.println(entrada.getClientes()[2]);
//        Cliente cliente = entrada.clientes[1];
//        cliente.setNome(jsonEntrada.getNome());
//        cliente.setIdade(jsonEntrada.getIdade());
//        cliente.setFilhos(jsonEntrada.getFilhos());

        return Response.status(Response.Status.OK.getStatusCode()).build();

    }


    @GET
    public Cliente listarCliente(){
        return entrada.getClientes()[1];
    }

}
