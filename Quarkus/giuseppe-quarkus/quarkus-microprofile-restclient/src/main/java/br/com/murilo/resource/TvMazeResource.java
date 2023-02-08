package br.com.murilo.resource;

import br.com.murilo.model.Episodes;
import br.com.murilo.model.TvSerie;
import br.com.murilo.proxy.TvMazeProxy;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Path("/tvseries")
public class TvMazeResource {

    private static final Logger LOG = Logger.getLogger(TvMazeResource.class);

    private List<TvSerie> tvSerie = new ArrayList();

    private Set<Episodes> genres = new HashSet<>();


    @Inject
    @RestClient
    TvMazeProxy proxy;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeries() throws MalformedURLException {
        LOG.info("Get TV Shows");
        insertGenres();
        insertShow();
        return Response.status(Response.Status.OK).entity(tvSerie).build();
    }


    @Path("/proxy")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getSeriesProxy(){
        return Response.status(Response.Status.OK).entity(proxy.listShows("Avatar")).build();
    }

    public void insertGenres(){
        genres.add(new Episodes("Ação"));
    }

    public void insertShow() throws MalformedURLException {
        tvSerie.add(new TvSerie(1L,"Avatar", new URL("https://google.com.br"), "Teste", "PT-BR", genres, new URL("https://google.com.br")));
    }

}