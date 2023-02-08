package br.com.murilo.rest;

import br.com.murilo.dto.CreateUserRequest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

//    @GET
//    public Response listUsers(){
//
//    }


    @POST
    public Response saveUser(CreateUserRequest createUserRequest){

        return Response.ok().build();

    }


}
