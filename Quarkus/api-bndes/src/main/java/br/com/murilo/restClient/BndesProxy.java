package br.com.murilo.restClient;

import br.com.murilo.model.bndes.EntradaLoteSolicitacoesHonraDTO;
import br.com.murilo.model.bndes.SolicitacaoHonraConsultaDTO;
import br.com.murilo.model.bndes.SolicitacaoRetorno;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

@Path("solicitacoes")
@RegisterRestClient(configKey = "api-bndes")
public interface BndesProxy {



    @GET
    @Path("operacao/credito-livre/{idOperacaoAgente}")
    public SolicitacaoHonraConsultaDTO listarSolicitacaoAgente(@HeaderParam("Authorization") String token, @PathParam("idOperacaoAgente") Long idOperacaoAgente);

    @GET
    @Path("/{idSolicitacao}")
    public SolicitacaoRetorno listarSolicitacao(@HeaderParam("Authorization") String token, @PathParam("idSolicitacao") Long idSolicitacao);

    @POST
    public SolicitacaoRetorno inserirSolicitacao(@HeaderParam("Authorization") String token, EntradaLoteSolicitacoesHonraDTO entradaLoteSolicitacoesHonraDTO);

}