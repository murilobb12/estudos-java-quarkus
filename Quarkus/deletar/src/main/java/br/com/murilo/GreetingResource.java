package br.com.murilo;

import br.com.murilo.service.HelloService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @Path("/print")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloService(){
        return HelloService.print();
    }
}