package br.com.murilo.rest;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/movies")
public class MovieResource {

    public static List<String> filmes = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMovies(){
        return Response.status(Response.Status.OK).entity(filmes).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    public Response sizeList(){
        return Response.status(Response.Status.OK).entity(filmes.size()).build();
    }

    @Path("/{newMovie}")
    public Response addMovie(@PathParam("newMovie")String newMovie){
        return Response.status(Response.Status.OK).entity(listMovies()).build();
    }

}
