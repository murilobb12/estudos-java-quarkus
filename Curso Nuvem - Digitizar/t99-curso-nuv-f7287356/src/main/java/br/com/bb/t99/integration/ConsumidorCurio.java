package br.com.bb.t99.integration;

import br.com.bb.dev.erros.curio.CurioExceptionMapper;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.bb.t99.operacao.cadastrarClienteV1.bean.requisicao.DadosRequisicaoCadastrarCliente;
import br.com.bb.t99.operacao.cadastrarClienteV1.bean.resposta.DadosRespostaCadastrarCliente;

@ApplicationScoped
@RegisterRestClient(configKey = "curio-host")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@RegisterProvider(CurioExceptionMapper.class)
public interface ConsumidorCurio {

  @POST
  @Path("op5207120v1")
  @IntegracaoIIB
  DadosRespostaCadastrarCliente executarOp5207120v1(
  DadosRequisicaoCadastrarCliente requisicao);

}
