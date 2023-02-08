package br.com.murilo.resource;

import br.com.murilo.model.bndes.EntradaLoteSolicitacoesHonraDTO;
import br.com.murilo.model.bndes.SolicitacaoHonraConsultaDTO;
import br.com.murilo.model.bndes.SolicitacaoRetorno;
import br.com.murilo.restClient.BndesProxy;
import br.com.murilo.service.BndesService;
import br.com.murilo.token.GerarToken;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;


@Path("bndes")
public class BndesResource {

    private static final Logger LOG = Logger.getLogger(BndesResource.class);

    @Inject
    @RestClient
    BndesProxy bndesProxy;

    @Inject
    GerarToken token;

    @Inject
    BndesService bndesService;

    @Inject
     EntradaLoteSolicitacoesHonraDTO entradaLoteSolicitacoesHonraDTO;

    /**
     * @GET Consultar resultado de um protocolo de solicitações de honra.
     * Essa consulta é através o id da solicitação gerado
     */
    @Path("solicitacoes/operacaoAgente/{idOperacaoAgente}")
    @GET
    public Response listarOperacaoAgente(@PathParam("idOperacaoAgente") Long idOperacaoAgente) throws IOException, InterruptedException {
        LOG.info("GET Solicitacao Agente Financeiro: " + idOperacaoAgente);
        return Response.status(Response.Status.OK).entity(bndesService.listarOperacaoAgente(idOperacaoAgente)).build();
    }


    /**
     * @GET Consultar solicitação de honra de uma operação de crédito livre.
     * Essa consulta é através da operação pelo Agente Financeiro
     */
    @Path("solicitacoes/{idSolicitacao}")
    @GET
    public Response listarSolicitacao(@PathParam("idSolicitacao") Long idSolicitacao) throws IOException, InterruptedException {
        LOG.info("GET Solicitacao Honra: " + idSolicitacao);
        return Response.status(Response.Status.OK).entity(bndesService.listarOperacao(idSolicitacao)).build();
    }


    /**
     * @POST Protocolar solicitações de honra.
     * Inserir solicitação de honra através do método POST, passando o lote de solicitações de honra
     */
    @Path("solicitacoes")
    @POST
    public Response inserirSolicitacoes(EntradaLoteSolicitacoesHonraDTO loteSolicitacoesHonraDTO) throws IOException, InterruptedException, ParseException {
        Log.info("POST Solicitacao Honra" + loteSolicitacoesHonraDTO);
        SolicitacaoRetorno solicitacaoRetorno = bndesProxy.inserirSolicitacao(token.gerarToken(), loteSolicitacoesHonraDTO);

        return Response.status(Response.Status.CREATED.getStatusCode()).entity(solicitacaoRetorno).build();
    }

}
