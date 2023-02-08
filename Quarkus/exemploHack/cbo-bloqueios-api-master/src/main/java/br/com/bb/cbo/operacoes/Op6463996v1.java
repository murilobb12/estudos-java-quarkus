package br.com.bb.cbo.operacoes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.opentracing.Traced;

import br.com.bb.cbo.identificacaoAcessoCorrespondente.IdentificacaoAcessoCorrespondenteService;
import br.com.bb.cbo.operacao.excluirBloqueioNegocialOperadorCorrespondenteBancarioV1.bean.requisicao.DadosRequisicaoExcluirBloqueioNegocialOperadorCorrespondenteBancario;
import br.com.bb.dev.ext.error.Erro;
import br.com.bb.dev.ext.exceptions.ErroNegocialException;

@Path("/op6463996v1")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8") 
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
@RequestScoped 
@Traced(value = true, operationName = "op6463996v1")
public class Op6463996v1 {
	
	@Inject
	IdentificacaoAcessoCorrespondenteService identificacaoAcessoCorrespondenteService;

	@POST
	@Timed(name = "requisicao", tags = { "operacao=6463996", "operacao_versao=1" }, absolute = true)
	@Operation(summary = "Excluir Bloqueio de Chave J.",
    description = "Exclui um Bloqueio Negocial do Operador do Correspondente Bancário.")
    @APIResponse(
    		responseCode = "200",
    		description = "Cliente",
    		content = { @Content(mediaType = "application/json",
    		schema = @Schema(implementation = Boolean.class))})
    @APIResponse(
    		responseCode = "400",
    		description = "Erro Negocial",
    		content = { @Content(mediaType = "application/json",
    		schema = @Schema(implementation = Erro.class))})
    @APIResponse(
    		responseCode = "500",
    		description = "Erro Sistema",
    		content = { @Content(mediaType = "application/json",
    		schema = @Schema(implementation = Erro.class))})
	public Response excluirBloqueioNegocialOperador(DadosRequisicaoExcluirBloqueioNegocialOperadorCorrespondenteBancario requisicao)
			throws ErroNegocialException {
		
		Integer codigoInstituicao = null;
		
		if (requisicao.getCodigoInstituicao() != 0) {
			codigoInstituicao = requisicao.getCodigoInstituicao();
		}
		
		return Response.status(Response.Status.OK).entity(
				identificacaoAcessoCorrespondenteService.excluirBloqueioNegocialOperador(
						codigoInstituicao, requisicao.getCodigoIdentificacaoCorrespondente(),
						requisicao.getCodigoChaveUsuario())).build();
	}
}
