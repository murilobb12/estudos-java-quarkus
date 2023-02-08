package br.com.bb.cbo.operacoes;

import java.util.List;

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

import br.com.bb.cbo.identificacaoAcessoCorrespondente.IdentificacaoAcessoCorrespondenteDao;
import br.com.bb.cbo.identificacaoAcessoCorrespondente.IdentificacaoAcessoCorrespondenteService;
import br.com.bb.cbo.identificacaoAcessoCorrespondente.ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar;
import br.com.bb.cbo.operacao.listarBloqueiosNegociaisOperadorCorrespondenteBancarioV1.bean.requisicao.DadosRequisicaoListarBloqueiosNegociaisOperadorCorrespondenteBancario;
import br.com.bb.cbo.operacao.listarBloqueiosNegociaisOperadorCorrespondenteBancarioV1.bean.resposta.DadosRespostaListarBloqueiosNegociaisOperadorCorrespondenteBancario;
import br.com.bb.cbo.operacao.listarBloqueiosNegociaisOperadorCorrespondenteBancarioV1.bean.resposta.ListaBloqueioNegocial;
import br.com.bb.dev.ext.error.Erro;
import br.com.bb.dev.ext.exceptions.ErroNegocialException;

@Path("/op6483891v1")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8") 
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8") 
@RequestScoped 
@Traced(value = true, operationName = "op6483891v1")
public class Op6483891v1 {
	
	@Inject
	IdentificacaoAcessoCorrespondenteService identificacaoAcessoCorrespondenteService;

	@POST
	@Timed(name = "requisicao", tags = { "operacao=6483891", "operacao_versao=1" }, absolute = true)
	@Operation(summary = "Listar Bloqueios de Chave J por código MCI.",
    description = "Retorna os Dados de Bloqueios Negociais.")
    @APIResponse(
    		responseCode = "200",
    		description = "Cliente",
    		content = { @Content(mediaType = "application/json",
    		schema = @Schema(implementation = DadosRespostaListarBloqueiosNegociaisOperadorCorrespondenteBancario.class))})
    @APIResponse(
    		responseCode = "422",
    		description = "Erro Negocial",
    		content = { @Content(mediaType = "application/json",
    		schema = @Schema(implementation = Erro.class))})
    @APIResponse(
    		responseCode = "500",
    		description = "Erro Sistema",
    		content = { @Content(mediaType = "application/json",
    		schema = @Schema(implementation = Erro.class))})
	public Response listarBloqueiosNegociaisOperador(DadosRequisicaoListarBloqueiosNegociaisOperadorCorrespondenteBancario requisicao)
			throws ErroNegocialException {
		
		char indicadorContinuacao = IdentificacaoAcessoCorrespondenteService.INDICADOR_CONTINUACAO_PAGINA_NAO;
		
		Integer codigoInstituicao = null;
		
		if (requisicao.getCodigoInstituicao() != 0) {
			codigoInstituicao = requisicao.getCodigoInstituicao();
		}
		
		List<ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar> listaDadosBloqueioNegocial = 
				identificacaoAcessoCorrespondenteService.listarBloqueiosNegociaisOperador(
						codigoInstituicao, requisicao.getCodigoOperadorCorrespondente(),
						requisicao.getNumeroPaginaPesquisa());
		
		if (listaDadosBloqueioNegocial.size() > IdentificacaoAcessoCorrespondenteDao.TAM_MAX_REGISTROS_POR_PAGINA_OPERACAO_LISTAR) {
			indicadorContinuacao = IdentificacaoAcessoCorrespondenteService.INDICADOR_CONTINUACAO_PAGINA_SIM;
		}
		
		// Remove todos os registros da lista que extrapolem o limite o tamanho de registros por página.
		while (listaDadosBloqueioNegocial.size() > IdentificacaoAcessoCorrespondenteDao.TAM_MAX_REGISTROS_POR_PAGINA_OPERACAO_LISTAR) {
			listaDadosBloqueioNegocial.remove(IdentificacaoAcessoCorrespondenteDao.TAM_MAX_REGISTROS_POR_PAGINA_OPERACAO_LISTAR + 1);
		}
		
		DadosRespostaListarBloqueiosNegociaisOperadorCorrespondenteBancario dadosRespostaListarBloqueiosNegociais =
				montaResposta(listaDadosBloqueioNegocial, indicadorContinuacao);
		
		dadosRespostaListarBloqueiosNegociais.setNumeroPaginaPesquisaRetorno(requisicao.getNumeroPaginaPesquisa());
		
		return Response.status(Response.Status.OK).entity(dadosRespostaListarBloqueiosNegociais).build();					

	}
	
	DadosRespostaListarBloqueiosNegociaisOperadorCorrespondenteBancario montaResposta(
			List<ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar> listaDadosBloqueioNegocial, char indicadorContinuacao) {
		
		DadosRespostaListarBloqueiosNegociaisOperadorCorrespondenteBancario resposta =
				new DadosRespostaListarBloqueiosNegociaisOperadorCorrespondenteBancario();
		
		resposta.setQuantidadeBloqueioNegocial(listaDadosBloqueioNegocial.size());
		resposta.setIndicadorContinuacao(String.valueOf(indicadorContinuacao));

		for (ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar dadosBloqueioNegocial : listaDadosBloqueioNegocial) {
			resposta.getListaBloqueioNegocial().add(
					converteObjetoModelBloqueioNegocialParaListaBloqueioNegocial(dadosBloqueioNegocial));
		}

		return resposta;
	}
	
	ListaBloqueioNegocial converteObjetoModelBloqueioNegocialParaListaBloqueioNegocial(
			ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar dadosBloqueioNegocial) {
		
		ListaBloqueioNegocial listaBloqueioNegocial = new ListaBloqueioNegocial();

		listaBloqueioNegocial.setCodigoCorrespondente(dadosBloqueioNegocial.getCodigoCorrespondente());
		listaBloqueioNegocial.setCodigoGestorRedeCorrespondente(dadosBloqueioNegocial.getCodigoGestorRedeCorrespondente());
		listaBloqueioNegocial.setCodigoInstituicao(dadosBloqueioNegocial.getCodigoInstituicao());
		listaBloqueioNegocial.setCodigoIdentificacaoCorrespondente(dadosBloqueioNegocial.getCodigoIdentificacaoCorrespondente());
		listaBloqueioNegocial.setCodigoOperadorCorrespondente(dadosBloqueioNegocial.getCodigoOperadorCorrespondente());
		listaBloqueioNegocial.setCodigoTipoBloqueioNegocial(dadosBloqueioNegocial.getCodigoTipoBloqueioNegocial());
		
		if (dadosBloqueioNegocial.getDataFimBloqueioNegocial() != null) {
			listaBloqueioNegocial.setDataFimBloqueioNegocial(dadosBloqueioNegocial.getDataFimBloqueioNegocial().toString());
		}
		
		if (dadosBloqueioNegocial.getDataInicioBloqueioNegocial() != null) {
			listaBloqueioNegocial.setDataInicioBloqueioNegocial(dadosBloqueioNegocial.getDataInicioBloqueioNegocial().toString());
		}
		
		listaBloqueioNegocial.setNomeCorrespondente(dadosBloqueioNegocial.getNomeCorrespondente());
		listaBloqueioNegocial.setNomeGestorRede(dadosBloqueioNegocial.getNomeGestorRede());
		listaBloqueioNegocial.setNomeOperadorCorrespondente(dadosBloqueioNegocial.getNomeOperadorCorrespondente());
		listaBloqueioNegocial.setNumeroCpfOperadorCorrespondente(dadosBloqueioNegocial.getNumeroCpfOperadorCorrespondente());
		listaBloqueioNegocial.setTextoTipoBloqueioNegocial(dadosBloqueioNegocial.getTextoTipoBloqueioNegocial());
		
		if (dadosBloqueioNegocial.getTimestampBloqueioNegocial() != null) {
			listaBloqueioNegocial.setTimestampBloqueioNegocial(dadosBloqueioNegocial.getTimestampBloqueioNegocial().toString());
		}
		
		listaBloqueioNegocial.setCodigoChaveUsuario(dadosBloqueioNegocial.getCodigoChaveUsuario());
		
		// O trecho de código abaixo, até o return, converte o Texto da Justificativa do Bloqueio para o formato
		// da classe Bean do Catálogo IIB.
		
		if (dadosBloqueioNegocial.getTextoJustificativaBloqueioNegocial() != null &&
				!dadosBloqueioNegocial.getTextoJustificativaBloqueioNegocial().isBlank()) {
			
			listaBloqueioNegocial.setTextoJustificativaBloqueioNegocial(
					dadosBloqueioNegocial.getTextoJustificativaBloqueioNegocial().trim());	
		}

		return listaBloqueioNegocial;
	}
}
