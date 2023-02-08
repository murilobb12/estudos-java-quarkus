package br.com.bb.cbo.resources;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.bb.mci.operacao.consultaDadosBasicosCodigoClienteV1.bean.requisicao.DadosRequisicaoConsultaDadosBasicosCodigoCliente;
import br.com.bb.mci.operacao.consultaDadosBasicosCodigoClienteV1.bean.resposta.DadosRespostaConsultaDadosBasicosCodigoCliente;

@RegisterRestClient
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface InterfaceConsumidor {

    @POST
    @Path("op207827v1")
    DadosRespostaConsultaDadosBasicosCodigoCliente executarOperacao(DadosRequisicaoConsultaDadosBasicosCodigoCliente requisicao);

}
