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
import br.com.bb.cbo.identificacaoAcessoCorrespondente.ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar;
import br.com.bb.cbo.operacao.consultarBloqueioNegocialOperadorCorrespondenteBancarioV1.bean.requisicao.DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario;
import br.com.bb.cbo.operacao.consultarBloqueioNegocialOperadorCorrespondenteBancarioV1.bean.resposta.DadosRespostaConsultarBloqueioNegocialOperadorCorrespondenteBancario;
import br.com.bb.dev.ext.error.Erro;
import br.com.bb.dev.ext.exceptions.ErroNegocialException;

@Path("/op6454625v1")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8") 
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
@RequestScoped 
@Traced(value = true, operationName = "op6454625v1")
public class Op6454625v1 {
	
	@Inject
	IdentificacaoAcessoCorrespondenteService identificacaoAcessoCorrespondenteService;

	@POST
	@Timed(name = "requisicao", tags = { "operacao=6454625", "operacao_versao=1" }, absolute = true)
	@Operation(summary = "Consultar Bloqueio de Chave J.",
    description = "Retorna os Dados de um Bloqueio Negocial.")
    @APIResponse(
    		responseCode = "200",
    		description = "Cliente",
    		content = { @Content(mediaType = "application/json",
    		schema = @Schema(implementation = DadosRespostaConsultarBloqueioNegocialOperadorCorrespondenteBancario.class))})
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
	public Response consultarBloqueioNegocialOperador(DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario requisicao)
			throws ErroNegocialException {
		
		Integer codigoInstituicao = null;
		
		if (requisicao.getCodigoInstituicao() != 0) {
			codigoInstituicao = requisicao.getCodigoInstituicao();
		}
		
		ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar dadosBloqueioNegocial = 
				identificacaoAcessoCorrespondenteService.consultarBloqueioNegocialOperador(
						codigoInstituicao, requisicao.getCodigoIdentificacaoCorrespondente());
		
		return Response.status(Response.Status.OK).entity(montaResposta(dadosBloqueioNegocial)).build();					

	}
	
	DadosRespostaConsultarBloqueioNegocialOperadorCorrespondenteBancario montaResposta(
			ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar dadosBloqueioNegocial) {
		
		DadosRespostaConsultarBloqueioNegocialOperadorCorrespondenteBancario resposta =
				new DadosRespostaConsultarBloqueioNegocialOperadorCorrespondenteBancario();

		resposta.setCodigoCorrespondente(dadosBloqueioNegocial.getCodigoCorrespondente());
		resposta.setCodigoGestorRedeCorrespondente(dadosBloqueioNegocial.getCodigoGestorRedeCorrespondente());
		resposta.setCodigoInstituicao(dadosBloqueioNegocial.getCodigoInstituicao());
		resposta.setCodigoIdentificacaoCorrespondente(dadosBloqueioNegocial.getCodigoIdentificacaoCorrespondente());
		resposta.setCodigoOperadorCorrespondente(dadosBloqueioNegocial.getCodigoOperadorCorrespondente());
		resposta.setCodigoTipoBloqueioNegocial(dadosBloqueioNegocial.getCodigoTipoBloqueioNegocial());
		
		if (dadosBloqueioNegocial.getDataFimBloqueioNegocial() != null) {
			resposta.setDataFimBloqueioNegocial(dadosBloqueioNegocial.getDataFimBloqueioNegocial().toString());
		}
		
		if (dadosBloqueioNegocial.getDataInicioBloqueioNegocial() != null) {
			resposta.setDataInicioBloqueioNegocial(dadosBloqueioNegocial.getDataInicioBloqueioNegocial().toString());
		}
		
		resposta.setNomeCorrespondente(dadosBloqueioNegocial.getNomeCorrespondente());
		resposta.setNomeGestorRede(dadosBloqueioNegocial.getNomeGestorRede());
		resposta.setNomeOperadorCorrespondente(dadosBloqueioNegocial.getNomeOperadorCorrespondente());
		resposta.setNumeroCpfOperadorCorrespondente(dadosBloqueioNegocial.getNumeroCpfOperadorCorrespondente());
		resposta.setTextoTipoBloqueioNegocial(dadosBloqueioNegocial.getTextoTipoBloqueioNegocial());
		
		if (dadosBloqueioNegocial.getTimestampBloqueioNegocial() != null) {
			resposta.setTimestampBloqueioNegocial(dadosBloqueioNegocial.getTimestampBloqueioNegocial().toString());
		}
		
		resposta.setCodigoChaveUsuario(dadosBloqueioNegocial.getCodigoChaveUsuario());
		
		// O trecho de código abaixo, até o return, converte o Texto da Justificativa do Bloqueio para o formato
		// da classe Bean do Catálogo IIB.
		
		int tamanhoTexto = 0;
		if (dadosBloqueioNegocial.getTextoJustificativaBloqueioNegocial() != null &&
				!dadosBloqueioNegocial.getTextoJustificativaBloqueioNegocial().isBlank()) {
			
			tamanhoTexto = dadosBloqueioNegocial.getTextoJustificativaBloqueioNegocial().trim().length();
			
			resposta.setTextoJustificativaBloqueioNegocial(dadosBloqueioNegocial.getTextoJustificativaBloqueioNegocial().trim());	
		}
		
		resposta.setQuantidadeTextoJustificativaBloqueio(tamanhoTexto);

		return resposta;
	}
}
