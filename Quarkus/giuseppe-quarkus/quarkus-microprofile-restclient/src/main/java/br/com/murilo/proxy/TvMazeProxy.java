package br.com.murilo.proxy;

import br.com.murilo.model.TvSerie;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Path("singlesearch")
@RegisterRestClient(configKey = "tvmaze")
public interface TvMazeProxy {

    @Path("/shows")
    @GET
    public TvSerie listShows(@QueryParam("q") String title);


}
