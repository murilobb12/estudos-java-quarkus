package br.com.murilo.resource;

import br.com.murilo.model.Movie;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Tag(name = "Movie Resource", description = "Movie REST APIs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/movies")
public class MovieResource {

    List<Movie> movieList = new ArrayList<>();

    @Operation(operationId = "getMovies",
            summary = "Get Movies",
            description = "Get all movies inside the list")
    @APIResponse(
            responseCode = "200",
            description = "Operation Completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @GET
    public Response listMovies() {

        return Response.status(Response.Status.OK.getStatusCode()).entity(movieList).build();

    }

    @Operation(
            operationId = "insertMovie",
            summary = "Insert Movie",
            description = "Insert movie inside the list"
    )
    @APIResponse(
            responseCode = "201",
            description = "Operation Completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @POST
    public Response insertMovie(
            @RequestBody(
                    description = "Movie to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Movie.class))
            )
            Movie newMovie) {
        movieList.add(newMovie);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @Operation(operationId = "countMovies",
            summary = "Count Movies",
            description = "Size of movie list")
    @APIResponse(
            responseCode = "200",
            description = "Operation Completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @GET
    @Path("/count")
    public Response countMovies() {

        int size = movieList.size();

        return Response.status(Response.Status.OK).entity(size).build();
    }

    @PUT
    public Response updateMovie(Movie updatedMovie) {

        movieList = movieList.stream().peek(movie -> {
            if (movie.getId().equals(updatedMovie.getId())) {
                movie.setTitle(updatedMovie.getTitle());
            }

        }).collect(Collectors.toList());


        return Response.status(Response.Status.OK.getStatusCode()).entity(movieList).build();

    }

    @DELETE
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id") Long id) {

        Optional<Movie> movieToDelete = movieList.stream().filter(movie -> movie.getId().equals(id)).findFirst();

        boolean removed = false;

        if (movieToDelete.isPresent()) {
            removed = movieList.remove(movieToDelete.get());
        }


        return removed ? Response.status(Response.Status.OK.getStatusCode()).build() :
                Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();

    }


}
