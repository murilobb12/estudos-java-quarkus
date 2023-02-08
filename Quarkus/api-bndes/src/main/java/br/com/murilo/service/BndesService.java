package br.com.murilo.service;

import br.com.murilo.model.bndes.SolicitacaoHonraConsultaDTO;
import br.com.murilo.model.bndes.SolicitacaoRetorno;
import br.com.murilo.restClient.BndesProxy;
import br.com.murilo.token.GerarToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.io.IOException;

@ApplicationScoped
public class BndesService {

    @Inject
    @RestClient
    BndesProxy bndesProxy;

    @Inject
    GerarToken token;

    public SolicitacaoHonraConsultaDTO listarOperacaoAgente(Long idOperacaoAgente) throws IOException, InterruptedException {

        try {
            return bndesProxy.listarSolicitacaoAgente(token.gerarToken(), idOperacaoAgente);
        } catch (ResteasyWebApplicationException e) {
            throw new NotFoundException();
        }

    }

    public SolicitacaoRetorno listarOperacao(Long idOperacao) throws IOException, InterruptedException {


        try {
            return bndesProxy.listarSolicitacao(token.gerarToken(), idOperacao);
        } catch (ResteasyWebApplicationException e) {
            throw new NotFoundException("Objeto não encontrado na nossa base de dados!");
        }

    }


}