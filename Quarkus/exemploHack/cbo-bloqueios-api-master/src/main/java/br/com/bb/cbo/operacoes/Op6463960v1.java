package br.com.bb.cbo.operacoes;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

import br.com.bb.cbo.exceptions.ChavesMonitoradasInstituicao;
import br.com.bb.cbo.exceptions.ErrosSistema;
import br.com.bb.cbo.identificacaoAcessoCorrespondente.IdentificacaoAcessoCorrespondenteService;
import br.com.bb.cbo.operacao.alterarBloqueioNegocialOperadorCorrespondenteBancarioV1.bean.requisicao.DadosRequisicaoAlterarBloqueioNegocialOperadorCorrespondenteBancario;
import br.com.bb.dev.ext.error.ChavesMonitoradasPadrao;
import br.com.bb.dev.ext.error.Erro;
import br.com.bb.dev.ext.exceptions.ErroNegocialException;

@Path("/op6463960v1")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8") 
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
@RequestScoped 
@Traced(value = true, operationName = "op6463960v1")
public class Op6463960v1 {
	
	@Inject
	IdentificacaoAcessoCorrespondenteService identificacaoAcessoCorrespondenteService;

	@POST
	@Timed(name = "requisicao", tags = { "operacao=6463960", "operacao_versao=1" }, absolute = true)
	@Operation(summary = "Alterar Bloqueio de Chave J.",
    description = "Altera um Bloqueio Negocial do Operador do Correspondente Bancário.")
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
	public Response alterarBloqueioNegocialOperador(DadosRequisicaoAlterarBloqueioNegocialOperadorCorrespondenteBancario requisicao)
			throws ErroNegocialException {
		
		Integer codigoInstituicao = null;
		
		if (requisicao.getCodigoInstituicao() != 0) {
			codigoInstituicao = requisicao.getCodigoInstituicao();
		}
		
		LocalDate dataFimBloqueioNegocial;
		
		if (requisicao.getDataFimBloqueioNegocial() == null || requisicao.getDataFimBloqueioNegocial().isBlank()) {
			dataFimBloqueioNegocial = null;
		} else {
			try {
				dataFimBloqueioNegocial = LocalDate.parse(requisicao.getDataFimBloqueioNegocial());
			} catch (DateTimeParseException e) {
				// Erro: Data fim inválida.
				throw new ErroNegocialException(ErrosSistema.ERRO_DATA_FIM_BLOQUEIO_INVALIDA.get()
	    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
	    						converteObjectParaString(requisicao.getDataFimBloqueioNegocial()))
	    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
	    						"Erro no parsing de dados da Op. Alterar Bloqueio Negocial: " +
	    						"A data do fim do bloqueio informada é inválida.")
	    				);
			}
		}
		
		return Response.status(Response.Status.OK).entity(
				identificacaoAcessoCorrespondenteService.alterarBloqueioNegocialOperador(
						codigoInstituicao, requisicao.getCodigoIdentificacaoCorrespondente(),
						dataFimBloqueioNegocial, requisicao.getCodigoChaveUsuario())).build();
	}
	
	// ******************************************************************************************************
	// OS MÉTODOS DA SEÇÃO A SEGUIR SÃO AUXILIARES E SÃO USADOS SOMENTE NESTA CLASSE (PRIVADOS).
	// 
	// ******************************************************************************************************
    
    private String converteObjectParaString(Object o) {
    	if (o != null) {
    		return o.toString();
    	} else {
    		return "";
    	}
    }
}
