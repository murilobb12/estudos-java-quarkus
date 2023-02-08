package br.com.bb.cbo.identificacaoAcessoCorrespondente;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;

import br.com.bb.cbo.correspondenteBancario.CorrespondenteBancarioService;
import br.com.bb.cbo.correspondenteBancario.ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio;
import br.com.bb.cbo.correspondenteBancario.ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio;
import br.com.bb.cbo.exceptions.ChavesMonitoradasCliente;
import br.com.bb.cbo.exceptions.ChavesMonitoradasConsumoOperacoesIIB;
import br.com.bb.cbo.exceptions.ChavesMonitoradasCorrespondenteBancario;
import br.com.bb.cbo.exceptions.ChavesMonitoradasDadosBloqueioOperadorCorrespondente;
import br.com.bb.cbo.exceptions.ChavesMonitoradasGestorRede;
import br.com.bb.cbo.exceptions.ChavesMonitoradasHistoricoCorrespondenteBancario;
import br.com.bb.cbo.exceptions.ChavesMonitoradasInstituicao;
import br.com.bb.cbo.exceptions.ChavesMonitoradasOperadorCorrespondente;
import br.com.bb.cbo.exceptions.ChavesMonitoradasUsuarioLogado;
import br.com.bb.cbo.exceptions.ErroSqlException;
import br.com.bb.cbo.exceptions.ErrosSistema;
import br.com.bb.cbo.gestorRedeCorrespondenteBancario.GestorRedeCorrespondenteBancarioService;
import br.com.bb.cbo.gestorRedeCorrespondenteBancario.ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio;
import br.com.bb.cbo.resources.InterfaceConsumidor;
import br.com.bb.dev.ext.error.ChavesMonitoradasPadrao;
import br.com.bb.dev.ext.exceptions.ErroNegocialException;
import br.com.bb.iib.curio.utils.exceptions.CurioNegocioException;
import br.com.bb.mci.operacao.consultaDadosBasicosCodigoClienteV1.bean.requisicao.DadosRequisicaoConsultaDadosBasicosCodigoCliente;
import br.com.bb.mci.operacao.consultaDadosBasicosCodigoClienteV1.bean.requisicao.Entrada;
import br.com.bb.mci.operacao.consultaDadosBasicosCodigoClienteV1.bean.resposta.DadosRespostaConsultaDadosBasicosCodigoCliente;
import io.opentracing.Tracer;

@RequestScoped
@Traced
public class IdentificacaoAcessoCorrespondenteService {
	
	private static final int CODIGO_ATIVO = 2;
	private static final int CODIGO_CANCELADO = 4;
	
	private static final int TAM_MAX_CAMPO_CODIGO_IDENTIFICACAO_CORRESPONDENTE = 8;
	private static final int TAM_MAX_CAMPO_CODIGO_CHAVE_USUARIO = 8;
	private static final int TAM_MAX_CAMPO_TEXTO_JUSTIFICATIVA = 500;
	
	// As duas constantes a seguir são utilizadas na exposição da operação (Classe br.com.bb.cbo.operacoes.Op6483891v1.java) 
	public static final char INDICADOR_CONTINUACAO_PAGINA_SIM = 'S';
	public static final char INDICADOR_CONTINUACAO_PAGINA_NAO = 'N';
	
	private static final boolean CONVERTER_PARA_MAIUSCULO = true;
	private static final boolean NAO_CONVERTER_PARA_MAIUSCULO = false;
	
	private static final String DATA_FIM_PADRAO = "9999-12-31"; 
	
	private static final String CAMPO_TEXTO_DADOS_NAO_LOCALIZADOS = "NÃO LOCALIZADO";
	private static final String CAMPO_TEXTO_SEM_GESTOR_REDE = "Sem Gestor de Rede";
	
	private static final int CODIGO_TIPO_BLOQUEIO_NEGOCIAL_PRATICAS_NAO_SUSTENTAVEIS = 1;
	private static final int CODIGO_TIPO_BLOQUEIO_NEGOCIAL_OUTROS = 3;
	
	private static final String[] TEXTOS_BLOQUEIO_NEGOCIAL_OPERADOR = 
		{"", "Práticas não sustentáveis", "Indícios de Golpe/fraude", "Outros"};
	
	private static final String[] TEXTOS_ESTADO_ATIVIDADE_BLOQUEIO = 
		{"", "", "ATIVO", "", "CANCELADO"};
	
    @Inject
    Tracer configuredTracer;
    
    @Inject
	Logger logger;
    
    @Inject
	IdentificacaoAcessoCorrespondenteDao identificacaoAcessoCorrespondenteDao;
    
    @Inject
	HistoricoIdentificacaoAcessoCorrespondenteDao historicoIdentificacaoAcessoCorrespondenteDao;
    
    @Inject
    CorrespondenteBancarioService correspondenteBancarioService;
    
    @Inject
    GestorRedeCorrespondenteBancarioService gestorRedeCorrespondenteBancarioService;
    
    @Inject
    @RestClient
    InterfaceConsumidor sidecarConsumidor;
	
    /* Consulta bloqueio negocial de uma Chave J (Código da Identificação do Correspondente) e uma Instituição
     * Parceira. A função retorna dados caso haja algum bloqueio ativo para os dados informados. Caso contrário,
     * lança-se um Erro Negocial.
     * */
    public ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar consultarBloqueioNegocialOperador(
    		Integer codigoInstituicao, String codigoIdentificacaoCorrespondente) throws ErroNegocialException {
    	
    	// Formata o campo Código de Identificação do Correspondente para eliminar espaços em branco antes e 
    	// depois do texto e converte para letras maiúsculas.
    	String codigoIdentificacaoCorrespondenteFormatado =
    			formatarCampoTexto(codigoIdentificacaoCorrespondente, CONVERTER_PARA_MAIUSCULO);
    	
    	// Valida se os parâmetros foram preenchidos e se respeitam os limites e domínios de valores.
    	validarDadosParaOperacaoConsultar(codigoInstituicao, codigoIdentificacaoCorrespondenteFormatado);
    	
    	try {
    	
	    	// Recupera o registro da tabela Identificação de Acesso do Correspondente (DB2CBO.IDFC_ACSS_CRS).
	    	ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente = 
	    			identificacaoAcessoCorrespondenteDao.consultarIdentificacaoAcessoCorrespondente(
	    					codigoInstituicao, codigoIdentificacaoCorrespondenteFormatado);
	    		
	    	if (identificacaoAcessoCorrespondente == null) {
	    		// Não foi localizado registro para a Instituição e Chave J informadas.
	    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_BUSCA_SEM_RESULTADO.get()
	    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
	    						converteObjectParaString(codigoInstituicao))
	    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
	    						converteObjectParaString(codigoIdentificacaoCorrespondenteFormatado))
	    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
	    						"Erro na Op. Consultar Bloqueio Negocial: " +
	    						"A Chave J informada não existe na tabela DB2CBO.IDFC_ACSS_CRS. " +
	    						"A Chave J pesquisada pode não existir ou não ser uma Chave J de Operador de Correspondente.")
	    				);
	    	} 
	    	
	    	if (!possuiBloqueioAtivo(identificacaoAcessoCorrespondente)) {
	    		// O registro foi localizado, mas não foi encontrado bloqueio ativo para os dados informados.
	    		throw new ErroNegocialException(ErrosSistema.ERRO_CODIGO_IDENTIFICACAO_CORRESPONDENTE_SEM_BLOQUEIO.get()
	    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
	    						converteObjectParaString(codigoInstituicao))
	    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
	    						converteObjectParaString(codigoIdentificacaoCorrespondenteFormatado))
	    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.CODIGO_ESTADO_ATIVIDADE_BLOQUEIO.get(),
	    						converteParaStringEstadoAtividadeBloqueio(
	    								identificacaoAcessoCorrespondente.getCodigoEstadoAtividadeBloqueio()))
	    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
	    						converteObjectParaString(identificacaoAcessoCorrespondente.getDataFimBloqueioNegocial()))
	    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
	    						"Erro na Op. Consultar Bloqueio Negocial: " +
	    						"A Chave J informada não possui bloqueio ativo na tabela DB2CBO.IDFC_ACSS_CRS.")
	    				);
	    	}
	    	
	    	// Obtém os dados do MCI e das tabelas de Correspondente Bancário e Gestor de Rede
	    	// que são necessários para montar a resposta da operação de Consultar Bloqueio Negocial.
	    	return montarObjetoRetorno(identificacaoAcessoCorrespondente);
	    	
    	} catch (ErroSqlException e) {
			// Erro de banco de dados.
			throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
					.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
							converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondenteFormatado))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Consultar Bloqueio Negocial: " +
    						"Houve algum erro de Banco de Dados ao realizar uma consulta à tabela DB2CBO.IDFC_ACSS_CRS.")
					);
		}
    }
    
    /* Lista todos os bloqueios negociais de Chave J de um MCI (Código do Operador do Correspondente) e uma Instituição
     * Parceira.
     * */
    public List<ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar> listarBloqueiosNegociaisOperador(
    		Integer codigoInstituicao, Integer codigoOperadorCorrespondente, Integer numeroPaginaPesquisa) throws ErroNegocialException {
		try {
			// Valida se os parâmetros foram preenchidos.
	    	validarDadosParaOperacaoListar(codigoInstituicao, codigoOperadorCorrespondente, numeroPaginaPesquisa);
			
			// Lista os Códigos da Identificação do Correspondente (Chaves J) vinculados a um Código do Operador
			// do Correspondente (MCI), a partir da tabela Identificação de Acesso do Correspondente.
			// Ou seja, lista as chaves J por MCI, com alguns outros campos/atributos auxiliares.
			List<ModelIdentificacaoAcessoCorrespondente> listaIdentificacaoAcessoCorrespondente =
					identificacaoAcessoCorrespondenteDao.listarIdentificacaoAcessoCorrespondente(
							codigoInstituicao, codigoOperadorCorrespondente, numeroPaginaPesquisa);
			
			// Se não há nenhuma chave J vinculada ao MCI pesquisado, retorna-se um Erro Negocial.
			if ((listaIdentificacaoAcessoCorrespondente == null) || (listaIdentificacaoAcessoCorrespondente.size() == 0)) {
    			throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_BUSCA_SEM_RESULTADO.get()
						.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
								converteObjectParaString(codigoInstituicao))
	    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_OPERADOR_CORRESPONDENTE.get(),
	    						converteObjectParaString(codigoOperadorCorrespondente))
	    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.NUMERO_PAGINA_PESQUISA.get(),
	    						converteObjectParaString(numeroPaginaPesquisa))
	    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
	    						"Erro na Op. Listar Bloqueios Negociais: " +
	    						"O cliente (MCI) informado não possui Chaves J de Operador de Correspondente Bancário " +
	    						"ou a página de pesquisa solicitada está fora dos limites de registros da tabela (DB2CBO.IDFC_ACSS_CRS).")
						);
    		}
			
			// Instancia uma lista vazia de bloqueios negociais. Essa lista será a saída de dados deste método.
			List<ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar> listaBloqueiosNegociais =
					new ArrayList<ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar>();
			
			// Para cada chave J, pesquisa se há bloqueio ativo para a mesma, ou seja, verifica-se se a chave J está na
			// Lista Restritiva.
			for (ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente : listaIdentificacaoAcessoCorrespondente) {
				// Se há bloqueio para uma dada chave J, adicionam-se os dados do bloqueio à lista de bloqueios negociais,
				// que será a saída de dados deste método.
				if (possuiBloqueioAtivo(identificacaoAcessoCorrespondente)) {
					listaBloqueiosNegociais.add(montarObjetoRetorno(identificacaoAcessoCorrespondente));
				}
			}
			
			if (listaBloqueiosNegociais.size() == 0) {
				// Se a lista de bloqueios negociais está vazia, o cliente não possui chave J com bloqueio.
				throw new ErroNegocialException(ErrosSistema.ERRO_CLIENTE_SEM_BLOQUEIOS.get()
						.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
								converteObjectParaString(codigoInstituicao))
	    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_OPERADOR_CORRESPONDENTE.get(),
	    						converteObjectParaString(codigoOperadorCorrespondente))
	    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.NUMERO_PAGINA_PESQUISA.get(),
	    						converteObjectParaString(numeroPaginaPesquisa))
	    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
	    						"Erro na Op. Listar Bloqueios Negociais: " +
	    						"O cliente (MCI) informado possui uma ou mais Chaves J de Operador de Correspondente Bancário, " +
	    						"mas nenhuma delas possui bloqueio ativo.")
						);
			}
			
			return listaBloqueiosNegociais;
		} catch (ErroSqlException e) {
			// Erro de banco de dados.
			throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
					.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
							converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_OPERADOR_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoOperadorCorrespondente))
    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.NUMERO_PAGINA_PESQUISA.get(),
    						converteObjectParaString(numeroPaginaPesquisa))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Listar Bloqueios Negociais: " +
    						"Houve algum erro de Banco de Dados ao listar dados da tabela DB2CBO.IDFC_ACSS_CRS. " +
    						"O número da página pesquisada indica quais registros serão retornados. " +
    						"Página 1: registros 1 a 100, Página 2: registros 101 a 200, etc.")
					);
		}
	}
    
    @Transactional(rollbackOn = Exception.class)
    public boolean incluirBloqueioNegocialOperador(Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		Integer codigoTipoBloqueioNegocial, String codigoChaveUsuario,
    		LocalDate dataFimBloqueioNegocial, String textoJustificativaBloqueioNegocial) throws ErroNegocialException {
    	
    	String codigoIdentificacaoCorrespondenteFormatado =
    			formatarCampoTexto(codigoIdentificacaoCorrespondente, CONVERTER_PARA_MAIUSCULO);
    	
    	String codigoChaveUsuarioFormatado = formatarCampoTexto(codigoChaveUsuario, CONVERTER_PARA_MAIUSCULO);
    	
    	String textoJustificativaBloqueioNegocialFormatado =
    			eliminarCaracteresInvalidos(formatarCampoTexto(textoJustificativaBloqueioNegocial, NAO_CONVERTER_PARA_MAIUSCULO));
    	
    	// Caso não tenha sido informada, a data fim padrão é configurada como 31/12/9999.
    	LocalDate dataFimBloqueioNegocialTratada = tratarCampoData(dataFimBloqueioNegocial, DATA_FIM_PADRAO); 
    	
        try {
        	ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente = 
        			validarDadosParaOperacaoIncluir(codigoInstituicao, codigoIdentificacaoCorrespondenteFormatado,
        		    		codigoTipoBloqueioNegocial, codigoChaveUsuarioFormatado,
        		    		dataFimBloqueioNegocialTratada, textoJustificativaBloqueioNegocialFormatado);
        	
        	LocalDate dataInicioBloqueioNegocial = LocalDate.now();
        	
        	Integer codigoEstadoAtividadeBloqueio = CODIGO_ATIVO;
        	
        	LocalDate dataAlteracao = LocalDate.now();
        	
        	LocalTime horaAlteracao = LocalTime.now();
        	
        	int qtdeLinhasInseridas = identificacaoAcessoCorrespondenteDao.incluirBloqueioNegocialOperador(
        			codigoInstituicao, codigoIdentificacaoCorrespondenteFormatado,
        			codigoTipoBloqueioNegocial, codigoChaveUsuarioFormatado, 
        			dataAlteracao, horaAlteracao,
        			dataInicioBloqueioNegocial, dataFimBloqueioNegocialTratada,
        			codigoEstadoAtividadeBloqueio, textoJustificativaBloqueioNegocialFormatado);
        	
        	if (qtdeLinhasInseridas == 0) {
        		// Erro de banco de dados.
        		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
        				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    							converteObjectParaString(codigoInstituicao))
        				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
        						converteObjectParaString(codigoIdentificacaoCorrespondente))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.CODIGO_TIPO_BLOQUEIO_NEGOCIAL.get(),
        						converteParaStringTipoBloqueio(codigoTipoBloqueioNegocial))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(dataFimBloqueioNegocial))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.TEXTO_JUSTIFICATIVA_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(textoJustificativaBloqueioNegocial))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.TIMESTAMP_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(Timestamp.valueOf(LocalDateTime.of(dataAlteracao, horaAlteracao))))
        				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
        						converteObjectParaString(codigoChaveUsuario))
        				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
        						"Erro na Op. Incluir Bloqueio Negocial: " +
        						"Houve algum erro de Banco de Dados ao realizar uma alteração na tabela DB2CBO.IDFC_ACSS_CRS.")
    					);
        	}
        	
        	ModelHistoricoIdentificacaoAcessoCorrespondente historicoIdentificacaoAcessoCorrespondente =
        			new ModelHistoricoIdentificacaoAcessoCorrespondente();
        	
        	// Preenche os campos do histórico não referentes a bloqueio, recuperados da tabela
        	// de Identificação de Acesso do Correspondente (DB2CBO.IDFC_ACSS_CRS).
        	historicoIdentificacaoAcessoCorrespondente.setCodigoEstadoAtividade(
        			identificacaoAcessoCorrespondente.getCodigoEstadoAtividade());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoCorrespondente(
        			identificacaoAcessoCorrespondente.getCodigoCorrespondente());
        	historicoIdentificacaoAcessoCorrespondente.setNumeroUltimoSequencialIdentificacao(
        			identificacaoAcessoCorrespondente.getNumeroUltimoSequencialIdentificacao());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoOperadorCorrespondente(
        			identificacaoAcessoCorrespondente.getCodigoOperadorCorrespondente());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoAcessoProvisorioOperador(
        			identificacaoAcessoCorrespondente.getCodigoAcessoProvisorioOperador());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoLojaCorrespondente(
        			identificacaoAcessoCorrespondente.getCodigoLojaCorrespondente());
        	
        	// Preenche os dados do histórico, com os dados do bloqueio.
        	historicoIdentificacaoAcessoCorrespondente.setCodigoInstituicao(codigoInstituicao);
        	historicoIdentificacaoAcessoCorrespondente.setCodigoIdentificacaoCorrespondente(codigoIdentificacaoCorrespondenteFormatado);
        	historicoIdentificacaoAcessoCorrespondente.setTimestampAtualizacaoHistorico(
        			Timestamp.valueOf(LocalDateTime.of(dataAlteracao, horaAlteracao)));
        	historicoIdentificacaoAcessoCorrespondente.setCodigoChaveUsuario(codigoChaveUsuarioFormatado);
        	historicoIdentificacaoAcessoCorrespondente.setDataAlteracao(dataAlteracao);
        	historicoIdentificacaoAcessoCorrespondente.setHoraAlteracao(horaAlteracao);
        	historicoIdentificacaoAcessoCorrespondente.setDataInicioBloqueioNegocial(dataInicioBloqueioNegocial);
        	historicoIdentificacaoAcessoCorrespondente.setDataFimBloqueioNegocial(dataFimBloqueioNegocialTratada);
        	historicoIdentificacaoAcessoCorrespondente.setCodigoEstadoAtividadeBloqueio(codigoEstadoAtividadeBloqueio);
        	historicoIdentificacaoAcessoCorrespondente.setCodigoTipoBloqueioNegocial(codigoTipoBloqueioNegocial);
        	historicoIdentificacaoAcessoCorrespondente.setTextoJustificativaBloqueioNegocial(textoJustificativaBloqueioNegocialFormatado);
        	
        	qtdeLinhasInseridas =
        			historicoIdentificacaoAcessoCorrespondenteDao.incluirHistoricoIdentificacaoAcessoCorrespondente(
        					historicoIdentificacaoAcessoCorrespondente);
        	
        	if (qtdeLinhasInseridas == 0) {
        		// Erro de banco de dados.
        		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
        				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    							converteObjectParaString(codigoInstituicao))
        				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
        						converteObjectParaString(codigoIdentificacaoCorrespondente))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.CODIGO_TIPO_BLOQUEIO_NEGOCIAL.get(),
        						converteParaStringTipoBloqueio(codigoTipoBloqueioNegocial))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(dataFimBloqueioNegocial))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.TEXTO_JUSTIFICATIVA_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(textoJustificativaBloqueioNegocial))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.TIMESTAMP_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(Timestamp.valueOf(LocalDateTime.of(dataAlteracao, horaAlteracao))))
        				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
        						converteObjectParaString(codigoChaveUsuario))
        				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
        						"Erro na Op. Incluir Bloqueio Negocial: " +
        						"Houve algum erro de Banco de Dados ao realizar uma inclusão na tabela DB2CBO.HST_IDFC_ACSS_CRS.")
    					);
        	}
        	
			return true;
		} catch (ErroSqlException e) {
			// Erro de banco de dados.
			throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
					.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
							converteObjectParaString(codigoInstituicao))
					.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
							converteObjectParaString(codigoIdentificacaoCorrespondente))
					.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.CODIGO_TIPO_BLOQUEIO_NEGOCIAL.get(),
							converteParaStringTipoBloqueio(codigoTipoBloqueioNegocial))
					.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
							converteObjectParaString(dataFimBloqueioNegocial))
					.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.TEXTO_JUSTIFICATIVA_BLOQUEIO_NEGOCIAL.get(),
							converteObjectParaString(textoJustificativaBloqueioNegocial))
					.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
							converteObjectParaString(codigoChaveUsuario))
					.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
							"Erro na Op. Incluir Bloqueio Negocial: " +
							"Houve algum erro de Banco de Dados ao realizar alteração e inclusão nas tabelas " +
							"DB2CBO.IDFC_ACSS_CRS e DB2CBO.HST_IDFC_ACSS_CRS, respectivamente.")
					);
		}
    }
    
    @Transactional(rollbackOn = Exception.class)
    public boolean alterarBloqueioNegocialOperador(Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		LocalDate dataFimBloqueioNegocial, String codigoChaveUsuario) throws ErroNegocialException {
    	
    	String codigoIdentificacaoCorrespondenteFormatado =
    			formatarCampoTexto(codigoIdentificacaoCorrespondente, CONVERTER_PARA_MAIUSCULO);
    	
    	String codigoChaveUsuarioFormatado = formatarCampoTexto(codigoChaveUsuario, CONVERTER_PARA_MAIUSCULO);
    	
    	// Caso não tenha sido informada, a data fim é configurada como 31/12/9999 (valor padrão, caso nula).
    	LocalDate dataFimBloqueioNegocialTratada = tratarCampoData(dataFimBloqueioNegocial, DATA_FIM_PADRAO);
    	
        try {
        	
        	ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente = 
        			validarDadosParaOperacaoAlterar(codigoInstituicao, codigoIdentificacaoCorrespondenteFormatado,
        					dataFimBloqueioNegocialTratada, codigoChaveUsuarioFormatado);
        	
        	LocalDate dataAlteracao = LocalDate.now();
        	
        	LocalTime horaAlteracao = LocalTime.now();
        	
        	int qtdeLinhasAlteradas = identificacaoAcessoCorrespondenteDao.alterarBloqueioNegocialOperador(
        			codigoInstituicao, codigoIdentificacaoCorrespondenteFormatado,
        			dataFimBloqueioNegocialTratada, codigoChaveUsuarioFormatado,
        			dataAlteracao, horaAlteracao);
        	
        	if (qtdeLinhasAlteradas == 0) {
        		// Erro de banco de dados.
        		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
        				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    							converteObjectParaString(codigoInstituicao))
        				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
        						converteObjectParaString(codigoIdentificacaoCorrespondente))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(dataFimBloqueioNegocial))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.TIMESTAMP_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(Timestamp.valueOf(LocalDateTime.of(dataAlteracao, horaAlteracao))))
        				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
        						converteObjectParaString(codigoChaveUsuario))
        				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
        						"Erro na Op. Alterar Bloqueio Negocial: " +
        						"Houve algum erro de Banco de Dados ao realizar uma alteração na tabela DB2CBO.IDFC_ACSS_CRS.")
    					);
        	}
        	
        	ModelHistoricoIdentificacaoAcessoCorrespondente historicoIdentificacaoAcessoCorrespondente =
        			new ModelHistoricoIdentificacaoAcessoCorrespondente();
        	
        	// Preenche os campos do histórico não referentes a bloqueio, recuperados da tabela
        	// de Identificação de Acesso do Correspondente (DB2CBO.IDFC_ACSS_CRS).
        	historicoIdentificacaoAcessoCorrespondente.setCodigoEstadoAtividade(
        			identificacaoAcessoCorrespondente.getCodigoEstadoAtividade());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoCorrespondente(
        			identificacaoAcessoCorrespondente.getCodigoCorrespondente());
        	historicoIdentificacaoAcessoCorrespondente.setNumeroUltimoSequencialIdentificacao(
        			identificacaoAcessoCorrespondente.getNumeroUltimoSequencialIdentificacao());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoOperadorCorrespondente(
        			identificacaoAcessoCorrespondente.getCodigoOperadorCorrespondente());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoAcessoProvisorioOperador(
        			identificacaoAcessoCorrespondente.getCodigoAcessoProvisorioOperador());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoLojaCorrespondente(
        			identificacaoAcessoCorrespondente.getCodigoLojaCorrespondente());
        	historicoIdentificacaoAcessoCorrespondente.setDataInicioBloqueioNegocial(
        			identificacaoAcessoCorrespondente.getDataInicioBloqueioNegocial());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoEstadoAtividadeBloqueio(
        			identificacaoAcessoCorrespondente.getCodigoEstadoAtividadeBloqueio());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoTipoBloqueioNegocial(
        			identificacaoAcessoCorrespondente.getCodigoTipoBloqueioNegocial());
        	historicoIdentificacaoAcessoCorrespondente.setTextoJustificativaBloqueioNegocial(
        			identificacaoAcessoCorrespondente.getTextoJustificativaBloqueioNegocial());
        	
        	// Preenche os dados do histórico, com os dados do bloqueio.
        	historicoIdentificacaoAcessoCorrespondente.setCodigoInstituicao(codigoInstituicao);
        	historicoIdentificacaoAcessoCorrespondente.setCodigoIdentificacaoCorrespondente(codigoIdentificacaoCorrespondenteFormatado);
        	historicoIdentificacaoAcessoCorrespondente.setTimestampAtualizacaoHistorico(
        			Timestamp.valueOf(LocalDateTime.of(dataAlteracao, horaAlteracao)));
        	historicoIdentificacaoAcessoCorrespondente.setCodigoChaveUsuario(codigoChaveUsuarioFormatado);
        	historicoIdentificacaoAcessoCorrespondente.setDataAlteracao(dataAlteracao);
        	historicoIdentificacaoAcessoCorrespondente.setHoraAlteracao(horaAlteracao);
        	historicoIdentificacaoAcessoCorrespondente.setDataFimBloqueioNegocial(dataFimBloqueioNegocialTratada);
        	
        	qtdeLinhasAlteradas =
        			historicoIdentificacaoAcessoCorrespondenteDao.incluirHistoricoIdentificacaoAcessoCorrespondente(
        					historicoIdentificacaoAcessoCorrespondente);
        	
        	if (qtdeLinhasAlteradas == 0) {
        		// Erro de banco de dados.
        		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
        				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    							converteObjectParaString(codigoInstituicao))
        				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
        						converteObjectParaString(codigoIdentificacaoCorrespondente))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(dataFimBloqueioNegocial))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.TIMESTAMP_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(Timestamp.valueOf(LocalDateTime.of(dataAlteracao, horaAlteracao))))
        				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
        						converteObjectParaString(codigoChaveUsuario))
        				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
        						"Erro na Op. Alterar Bloqueio Negocial: " +
        						"Houve algum erro de Banco de Dados ao realizar uma inclusão na tabela DB2CBO.HST_IDFC_ACSS_CRS.")
    					);
        	}
        	
			return true;
		} catch (ErroSqlException e) {
			// Erro de banco de dados.
			throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
							converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
    						converteObjectParaString(dataFimBloqueioNegocial))
    				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
    						converteObjectParaString(codigoChaveUsuario))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Alterar Bloqueio Negocial: " +
    						"Houve algum erro de Banco de Dados ao realizar alteração e inclusão nas tabelas " +
    						"DB2CBO.IDFC_ACSS_CRS e DB2CBO.HST_IDFC_ACSS_CRS, respectivamente.")
					);
		}
    }
    
    @Transactional(rollbackOn = Exception.class)
    public boolean excluirBloqueioNegocialOperador(Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		String codigoChaveUsuario) throws ErroNegocialException {
    	
    	String codigoIdentificacaoCorrespondenteFormatado =
    			formatarCampoTexto(codigoIdentificacaoCorrespondente, CONVERTER_PARA_MAIUSCULO);
    	
    	String codigoChaveUsuarioFormatado = formatarCampoTexto(codigoChaveUsuario, CONVERTER_PARA_MAIUSCULO);
    	
        try {
        	ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente =
        			validarDadosParaOperacaoExcluir(codigoInstituicao, codigoIdentificacaoCorrespondente, codigoChaveUsuario);
        	
        	Integer codigoEstadoAtividadeBloqueio = CODIGO_CANCELADO;
        	
        	LocalDate dataAlteracao = LocalDate.now();
        	
        	LocalTime horaAlteracao = LocalTime.now();
        	
        	int qtdeLinhasAlteradas = identificacaoAcessoCorrespondenteDao.excluirBloqueioNegocialOperador(
        			codigoInstituicao, codigoIdentificacaoCorrespondenteFormatado,
        			codigoChaveUsuarioFormatado, dataAlteracao,
        			horaAlteracao, codigoEstadoAtividadeBloqueio);
        	
        	if (qtdeLinhasAlteradas == 0) {
        		// Erro de banco de dados.
        		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
        				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    							converteObjectParaString(codigoInstituicao))
        				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
        						converteObjectParaString(codigoIdentificacaoCorrespondente))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.TIMESTAMP_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(Timestamp.valueOf(LocalDateTime.of(dataAlteracao, horaAlteracao))))
        				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
        						converteObjectParaString(codigoChaveUsuario))
        				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
        						"Erro na Op. Excluir Bloqueio Negocial: " +
        						"Houve algum erro de Banco de Dados ao realizar uma alteração na tabela DB2CBO.IDFC_ACSS_CRS.")
    					);
        	}
        	
        	ModelHistoricoIdentificacaoAcessoCorrespondente historicoIdentificacaoAcessoCorrespondente =
        			new ModelHistoricoIdentificacaoAcessoCorrespondente();
        	
        	// Preenche os campos do histórico não referentes a bloqueio, recuperados da tabela
        	// de Identificação de Acesso do Correspondente (DB2CBO.IDFC_ACSS_CRS).
        	historicoIdentificacaoAcessoCorrespondente.setCodigoEstadoAtividade(
        			identificacaoAcessoCorrespondente.getCodigoEstadoAtividade());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoCorrespondente(
        			identificacaoAcessoCorrespondente.getCodigoCorrespondente());
        	historicoIdentificacaoAcessoCorrespondente.setNumeroUltimoSequencialIdentificacao(
        			identificacaoAcessoCorrespondente.getNumeroUltimoSequencialIdentificacao());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoOperadorCorrespondente(
        			identificacaoAcessoCorrespondente.getCodigoOperadorCorrespondente());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoAcessoProvisorioOperador(
        			identificacaoAcessoCorrespondente.getCodigoAcessoProvisorioOperador());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoLojaCorrespondente(
        			identificacaoAcessoCorrespondente.getCodigoLojaCorrespondente());
        	historicoIdentificacaoAcessoCorrespondente.setDataInicioBloqueioNegocial(
        			identificacaoAcessoCorrespondente.getDataInicioBloqueioNegocial());
        	historicoIdentificacaoAcessoCorrespondente.setDataFimBloqueioNegocial(
        			identificacaoAcessoCorrespondente.getDataFimBloqueioNegocial());
        	historicoIdentificacaoAcessoCorrespondente.setCodigoTipoBloqueioNegocial(
        			identificacaoAcessoCorrespondente.getCodigoTipoBloqueioNegocial());
        	historicoIdentificacaoAcessoCorrespondente.setTextoJustificativaBloqueioNegocial(
        			identificacaoAcessoCorrespondente.getTextoJustificativaBloqueioNegocial());
        	
        	// Preenche os dados do histórico, com os dados do bloqueio.
        	historicoIdentificacaoAcessoCorrespondente.setCodigoInstituicao(codigoInstituicao);
        	historicoIdentificacaoAcessoCorrespondente.setCodigoIdentificacaoCorrespondente(codigoIdentificacaoCorrespondenteFormatado);
        	historicoIdentificacaoAcessoCorrespondente.setTimestampAtualizacaoHistorico(
        			Timestamp.valueOf(LocalDateTime.of(dataAlteracao, horaAlteracao)));
        	historicoIdentificacaoAcessoCorrespondente.setCodigoChaveUsuario(codigoChaveUsuarioFormatado);
        	historicoIdentificacaoAcessoCorrespondente.setDataAlteracao(dataAlteracao);
        	historicoIdentificacaoAcessoCorrespondente.setHoraAlteracao(horaAlteracao);
        	historicoIdentificacaoAcessoCorrespondente.setCodigoEstadoAtividadeBloqueio(codigoEstadoAtividadeBloqueio);
        	
        	qtdeLinhasAlteradas =
        			historicoIdentificacaoAcessoCorrespondenteDao.incluirHistoricoIdentificacaoAcessoCorrespondente(
        					historicoIdentificacaoAcessoCorrespondente);
        	
        	if (qtdeLinhasAlteradas == 0) {
        		// Erro de banco de dados.
        		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
        				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    							converteObjectParaString(codigoInstituicao))
        				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
        						converteObjectParaString(codigoIdentificacaoCorrespondente))
        				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.TIMESTAMP_BLOQUEIO_NEGOCIAL.get(),
        						converteObjectParaString(Timestamp.valueOf(LocalDateTime.of(dataAlteracao, horaAlteracao))))
        				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
        						converteObjectParaString(codigoChaveUsuario))
        				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
        						"Erro na Op. Excluir Bloqueio Negocial: " +
        						"Houve algum erro de Banco de Dados ao realizar uma inclusão na tabela DB2CBO.HST_IDFC_ACSS_CRS.")
    					);
        	}
        	
			return true;
		} catch (ErroSqlException e) {
			// Erro de banco de dados.
			throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
							converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
    						converteObjectParaString(codigoChaveUsuario))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Excluir Bloqueio Negocial: " +
    						"Houve algum erro de Banco de Dados ao realizar alteração e inclusão nas tabelas " +
    						"DB2CBO.IDFC_ACSS_CRS e DB2CBO.HST_IDFC_ACSS_CRS, respectivamente.")
					);
		}
    }

	// ******************************************************************************************************
	// OS MÉTODOS DA SEÇÃO A SEGUIR SÃO AUXILIARES E SÃO USADOS SOMENTE NESTA CLASSE (PRIVADOS).
	// 
	// ******************************************************************************************************
    
    private String converteParaStringEstadoAtividadeBloqueio(Integer codigoEstadoAtividadeBloqueio) {
    	if (codigoEstadoAtividadeBloqueio != null &&
    			codigoEstadoAtividadeBloqueio.intValue() >= CODIGO_ATIVO &&
    			codigoEstadoAtividadeBloqueio.intValue() <= CODIGO_CANCELADO) {
    		return codigoEstadoAtividadeBloqueio.toString() + " - " + TEXTOS_ESTADO_ATIVIDADE_BLOQUEIO[codigoEstadoAtividadeBloqueio];
    	} else {
    		return "";
    	}
    }
    
    private String converteParaStringTipoBloqueio(Integer codigoTipoBloqueioNegocial) {
    	if (codigoTipoBloqueioNegocial != null &&
    			codigoTipoBloqueioNegocial.intValue() >= CODIGO_TIPO_BLOQUEIO_NEGOCIAL_PRATICAS_NAO_SUSTENTAVEIS &&
    					codigoTipoBloqueioNegocial.intValue() <= CODIGO_TIPO_BLOQUEIO_NEGOCIAL_OUTROS) {
    		return codigoTipoBloqueioNegocial.toString() + " - " + TEXTOS_BLOQUEIO_NEGOCIAL_OPERADOR[codigoTipoBloqueioNegocial];
    	} else {
    		return "";
    	}
    }
    
    private String converteObjectParaString(Object o) {
    	if (o != null) {
    		return o.toString();
    	} else {
    		return "";
    	}
    }
	
    private String formatarCampoTexto(String campo, boolean converterParaMaiusculo) {
    	String campoFormatado;
    	
    	if (campo == null || campo.isBlank()) {
    		campoFormatado = null;
        } else {
        	if (converterParaMaiusculo) {
        		campoFormatado = campo.trim().toUpperCase();
        	} else {
        		campoFormatado = campo.trim();
        	}
        }
    	
    	return campoFormatado;
    }
    
    private String eliminarCaracteresInvalidos(String campo) {
    	
    	if (campo == null || campo.isBlank()) {
    		return null;
        } else {
        	return campo.replace('\n', ' ').replace('\t', ' ').replace('\r', ' ');
        }
    }
    
    private LocalDate tratarCampoData(LocalDate data, String dataPadrao) {
    	
    	LocalDate dataTratada;
    	
    	if (data == null) {
    		dataTratada = LocalDate.parse(dataPadrao);
    	} else {
    		dataTratada = data;
    	}
    	
    	return dataTratada;
    }
    
    private void validarDadosParaOperacaoConsultar(
    		Integer codigoInstituicao, String codigoIdentificacaoCorrespondente) throws ErroNegocialException {
    	if (codigoInstituicao == null ||
    			codigoIdentificacaoCorrespondente == null) {
    		// Erro: campos obrigatórios não preenchidos.
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_REQUISICAO_SEM_VALORES.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Consultar Bloqueio Negocial: " +
    						"Campos obrigatórios não foram preenchidos.")
    				);
    	}
    	
    	if (codigoIdentificacaoCorrespondente.length() > TAM_MAX_CAMPO_CODIGO_IDENTIFICACAO_CORRESPONDENTE) {
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_REQUISICAO_DADOS_INVALIDOS.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Consultar Bloqueio Negocial: " +
    						"A Chave J de Operador informada possui mais de 8 caracteres.")
    				);
    	}
    }
    
    private void validarDadosParaOperacaoListar(
    		Integer codigoInstituicao, Integer codigoOperadorCorrespondente, Integer numeroPaginaPesquisa) throws ErroNegocialException {
    	if (codigoInstituicao == null ||
    			codigoOperadorCorrespondente == null ||
    			numeroPaginaPesquisa == null) {
    		// Erro: campos obrigatórios não preenchidos.
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_REQUISICAO_SEM_VALORES.get()
					.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
							converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_OPERADOR_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoOperadorCorrespondente))
    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.NUMERO_PAGINA_PESQUISA.get(),
    						converteObjectParaString(numeroPaginaPesquisa))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Listar Bloqueios Negociais: " +
    						"Campos obrigatórios não foram preenchidos.")
					);
    	}
    	
    	if (numeroPaginaPesquisa <= 0) {
    		// Erro: campos número da página de pesquisa inválido.
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_REQUISICAO_DADOS_INVALIDOS.get()
					.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
							converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_OPERADOR_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoOperadorCorrespondente))
    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.NUMERO_PAGINA_PESQUISA.get(),
    						converteObjectParaString(numeroPaginaPesquisa))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Listar Bloqueios Negociais: " +
    						"A página de pesquisa solicitada deve ser maior ou igual a 1 (um).")
					);
    	}
    }
    
    public ModelIdentificacaoAcessoCorrespondente validarDadosParaOperacaoIncluir(
    		Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		Integer codigoTipoBloqueioNegocial, String codigoChaveUsuario,
    		LocalDate dataFimBloqueioNegocial, String textoJustificativaBloqueioNegocial) throws ErroNegocialException {
    	
    	validarSeCamposNaoSaoNulosParaOperacaoIncluir(codigoInstituicao, codigoIdentificacaoCorrespondente, codigoTipoBloqueioNegocial,
    			codigoChaveUsuario, dataFimBloqueioNegocial, textoJustificativaBloqueioNegocial);
    	
    	if (codigoChaveUsuario.length() != TAM_MAX_CAMPO_CODIGO_CHAVE_USUARIO) {
    		// Erro: Chave do Usuário deve possuir 8 caracteres.
    		lancarErroNegocialParaOperacaoIncluirBloqueio(ErrosSistema.ERRO_GENERICO_REQUISICAO_DADOS_INVALIDOS,
    	    		codigoInstituicao, codigoIdentificacaoCorrespondente,
    	    		codigoTipoBloqueioNegocial, codigoChaveUsuario,
    	    		dataFimBloqueioNegocial, textoJustificativaBloqueioNegocial,
    	    		"Erro na Op. Incluir Bloqueio Negocial: " +
    				"O campo Código Chave do Usuário foi informado com mais de 8 caracteres.");
    	}
    	
    	validarCampoTextoJustificativaParaOperacaoIncluir(codigoInstituicao, codigoIdentificacaoCorrespondente,
    			codigoTipoBloqueioNegocial, codigoChaveUsuario, dataFimBloqueioNegocial, textoJustificativaBloqueioNegocial);
    	
    	// A funcionalidade deverá permitir descrever o motivo do bloqueio temporário de uma Chave J
    	// entre as opções 1 - Práticas não sustentáveis, 2 - indícios de Golpe/fraude, 3 - Outros.
    	if (codigoTipoBloqueioNegocial < CODIGO_TIPO_BLOQUEIO_NEGOCIAL_PRATICAS_NAO_SUSTENTAVEIS ||
    			codigoTipoBloqueioNegocial > CODIGO_TIPO_BLOQUEIO_NEGOCIAL_OUTROS) {
    		// Erro: Código do Tipo do Bloqueio inválido.
    		lancarErroNegocialParaOperacaoIncluirBloqueio(ErrosSistema.ERRO_CODIGO_TIPO_BLOQUEIO_INVALIDO,
    	    		codigoInstituicao, codigoIdentificacaoCorrespondente,
    	    		codigoTipoBloqueioNegocial, codigoChaveUsuario,
    	    		dataFimBloqueioNegocial, textoJustificativaBloqueioNegocial,
    	    		"Erro na Op. Incluir Bloqueio Negocial: " +
    				"O código do Tipo do Bloqueio informado é inválido.");
    	}
    	
    	if (dataFimBloqueioNegocial != null && dataFimBloqueioNegocial.isBefore(LocalDate.now())) {
    		// Erro: Não é possível incluir bloqueio com uma data fim passada.
    		lancarErroNegocialParaOperacaoIncluirBloqueio(ErrosSistema.ERRO_DATA_FIM_BLOQUEIO_MENOR_QUE_DATA_ATUAL,
    	    		codigoInstituicao, codigoIdentificacaoCorrespondente,
    	    		codigoTipoBloqueioNegocial, codigoChaveUsuario,
    	    		dataFimBloqueioNegocial, textoJustificativaBloqueioNegocial,
    	    		"Erro na Op. Incluir Bloqueio Negocial: " +
    				"A data do fim do bloqueio informada é anterior à data atual.");
    	}
    	
    	// Realiza uma consulta à tabela Identificação de Acesso do Correspondente (DB2CBO.IDFC_ACSS_CRS).
    	ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente = 
				identificacaoAcessoCorrespondenteDao.consultarIdentificacaoAcessoCorrespondente(
						codigoInstituicao, codigoIdentificacaoCorrespondente);
    	
    	if (identificacaoAcessoCorrespondente == null) {
    		// Erro: Chave J não localizada.
    		lancarErroNegocialParaOperacaoIncluirBloqueio(ErrosSistema.ERRO_CODIGO_IDENTIFICACAO_CORRESPONDENTE_NAO_LOCALIZADO,
    	    		codigoInstituicao, codigoIdentificacaoCorrespondente,
    	    		codigoTipoBloqueioNegocial, codigoChaveUsuario,
    	    		dataFimBloqueioNegocial, textoJustificativaBloqueioNegocial,
    	    		"Erro na Op. Incluir Bloqueio Negocial: " +
    				"A Chave J informada não existe na tabela DB2CBO.IDFC_ACSS_CRS. " +
    	    		"A Chave J informada pode não existir ou não ser uma Chave J de Operador de Correspondente.");
    	}
    	
    	if (possuiBloqueioAtivo(identificacaoAcessoCorrespondente)) {
    		// Erro: Não é permitido incluir novo bloqueio para uma chave J com bloqueio vigente.
    		lancarErroNegocialParaOperacaoIncluirBloqueio(ErrosSistema.ERRO_CODIGO_IDENTIFICACAO_CORRESPONDENTE_POSSUI_BLOQUEIO_ATIVO,
    	    		codigoInstituicao, codigoIdentificacaoCorrespondente,
    	    		codigoTipoBloqueioNegocial, codigoChaveUsuario,
    	    		dataFimBloqueioNegocial, textoJustificativaBloqueioNegocial,
    	    		"Erro na Op. Incluir Bloqueio Negocial: " +
    				"A Chave J informada já possui um bloqueio ativo na tabela DB2CBO.IDFC_ACSS_CRS.");
    	} 
    	
    	return identificacaoAcessoCorrespondente;
    }
    
    public void validarSeCamposNaoSaoNulosParaOperacaoIncluir(Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		Integer codigoTipoBloqueioNegocial, String codigoChaveUsuario,
    		LocalDate dataFimBloqueioNegocial, String textoJustificativaBloqueioNegocial) throws ErroNegocialException {
    	
    	if (codigoInstituicao == null ||
    			codigoIdentificacaoCorrespondente == null ||
    					codigoTipoBloqueioNegocial == null ||
    	    			codigoChaveUsuario == null) {
    		// Erro: campos obrigatórios não preenchidos.
    		lancarErroNegocialParaOperacaoIncluirBloqueio(ErrosSistema.ERRO_GENERICO_REQUISICAO_SEM_VALORES,
    	    		codigoInstituicao, codigoIdentificacaoCorrespondente,
    	    		codigoTipoBloqueioNegocial, codigoChaveUsuario,
    	    		dataFimBloqueioNegocial, textoJustificativaBloqueioNegocial,
    	    		"Erro na Op. Incluir Bloqueio Negocial: " +
    				"Campos obrigatórios não foram preenchidos.");
    	}
    }
    
    public void validarCampoTextoJustificativaParaOperacaoIncluir(Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		Integer codigoTipoBloqueioNegocial, String codigoChaveUsuario,
    		LocalDate dataFimBloqueioNegocial, String textoJustificativaBloqueioNegocial) throws ErroNegocialException {
    	
    	if (codigoTipoBloqueioNegocial == CODIGO_TIPO_BLOQUEIO_NEGOCIAL_OUTROS &&
    			textoJustificativaBloqueioNegocial == null) {
    		// Erro: Texto justificativa é obrigatório para a opção 3 - Outros.
    		lancarErroNegocialParaOperacaoIncluirBloqueio(ErrosSistema.ERRO_TEXTO_JUSTIFICATIVA_INVALIDO,
    	    		codigoInstituicao, codigoIdentificacaoCorrespondente,
    	    		codigoTipoBloqueioNegocial, codigoChaveUsuario,
    	    		dataFimBloqueioNegocial, textoJustificativaBloqueioNegocial,
    	    		"Erro na Op. Incluir Bloqueio Negocial: " +
    				"O texto de justificativa do bloqueio não foi informado.");
    	}
    	
    	if (textoJustificativaBloqueioNegocial != null &&
    			textoJustificativaBloqueioNegocial.length() > TAM_MAX_CAMPO_TEXTO_JUSTIFICATIVA) {
    		// Erro: Texto justificativa não pode ter mais de 500 caracteres.
    		lancarErroNegocialParaOperacaoIncluirBloqueio(ErrosSistema.ERRO_TEXTO_JUSTIFICATIVA_EXPIROU_TAMANHO_MAXIMO,
    	    		codigoInstituicao, codigoIdentificacaoCorrespondente,
    	    		codigoTipoBloqueioNegocial, codigoChaveUsuario,
    	    		dataFimBloqueioNegocial, textoJustificativaBloqueioNegocial,
    	    		"Erro na Op. Incluir Bloqueio Negocial: " +
    				"O texto de justificativa do bloqueio foi informado com mais de 500 caracteres.");
    	}
    }
    
    public void lancarErroNegocialParaOperacaoIncluirBloqueio(ErrosSistema erroSistema,
    		Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		Integer codigoTipoBloqueioNegocial, String codigoChaveUsuario,
    		LocalDate dataFimBloqueioNegocial, String textoJustificativaBloqueioNegocial,
    		String motivoErro) throws ErroNegocialException {
    	
    	throw new ErroNegocialException(erroSistema.get()
				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
						converteObjectParaString(codigoInstituicao))
				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
						converteObjectParaString(codigoIdentificacaoCorrespondente))
				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.CODIGO_TIPO_BLOQUEIO_NEGOCIAL.get(),
						converteParaStringTipoBloqueio(codigoTipoBloqueioNegocial))
				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
						converteObjectParaString(dataFimBloqueioNegocial))
				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.TEXTO_JUSTIFICATIVA_BLOQUEIO_NEGOCIAL.get(),
						converteObjectParaString(textoJustificativaBloqueioNegocial))
				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
						converteObjectParaString(codigoChaveUsuario))
				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(), motivoErro)
				);
    }
    
    public boolean possuiBloqueioAtivo(ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente) {
    	
    	if (identificacaoAcessoCorrespondente.getCodigoEstadoAtividadeBloqueio() != null &&
    			identificacaoAcessoCorrespondente.getCodigoEstadoAtividadeBloqueio() == CODIGO_ATIVO &&
    			identificacaoAcessoCorrespondente.getDataFimBloqueioNegocial() != null &&
    			!identificacaoAcessoCorrespondente.getDataFimBloqueioNegocial().isBefore(LocalDate.now())) {
    		return true;
    	}
    	return false;
    }
    
    public ModelIdentificacaoAcessoCorrespondente validarDadosParaOperacaoAlterar(
    		Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		LocalDate dataFimBloqueioNegocial, String codigoChaveUsuario) throws ErroNegocialException {
    	if (codigoInstituicao == null ||
    			codigoIdentificacaoCorrespondente == null ||
    			codigoChaveUsuario == null) {
    		// Erro: campos obrigatórios não preenchidos.
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_REQUISICAO_SEM_VALORES.get()
					.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
							converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
    						converteObjectParaString(dataFimBloqueioNegocial))
    				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
    						converteObjectParaString(codigoChaveUsuario))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Alterar Bloqueio Negocial: " +
    						"Campos obrigatórios não foram preenchidos.")
					);
    	}
    	
    	ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente = 
    			identificacaoAcessoCorrespondenteDao.consultarIdentificacaoAcessoCorrespondente(
						codigoInstituicao, codigoIdentificacaoCorrespondente);
    	
    	if (identificacaoAcessoCorrespondente == null) {
    		// Erro: Chave J não localizada.
    		throw new ErroNegocialException(ErrosSistema.ERRO_CODIGO_IDENTIFICACAO_CORRESPONDENTE_NAO_LOCALIZADO.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
    						converteObjectParaString(dataFimBloqueioNegocial))
    				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
    						converteObjectParaString(codigoChaveUsuario))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Alterar Bloqueio Negocial: " +
    						"A Chave J informada não existe na tabela DB2CBO.IDFC_ACSS_CRS. " +
    	    				"A Chave J informada pode não existir ou não ser uma Chave J de Operador de Correspondente.")
    				);
    	}
    	
    	if (!possuiBloqueioAtivo(identificacaoAcessoCorrespondente)) {
    		// Erro: Chave J não possui bloqueio ativo para alteração.
    		throw new ErroNegocialException(ErrosSistema.ERRO_ALTERAR_BLOQUEIO_NAO_ATIVO.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
    						converteObjectParaString(dataFimBloqueioNegocial))
    				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
    						converteObjectParaString(codigoChaveUsuario))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Alterar Bloqueio Negocial: " +
    						"A Chave J informada não possui bloqueio ativo na tabela DB2CBO.IDFC_ACSS_CRS.")
    				);
    	}
    	
    	if (identificacaoAcessoCorrespondente.getDataFimBloqueioNegocial().isEqual(dataFimBloqueioNegocial)) {
    		// Erro: Não houve alteração dos dados (Data fim do bloqueio).
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_ALTERAR_REGISTRO_SEM_DADOS_ALTERADOS.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
    						converteObjectParaString(dataFimBloqueioNegocial))
    				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
    						converteObjectParaString(codigoChaveUsuario))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Alterar Bloqueio Negocial: " +
    						"Não houve alteração dos dados (data fim do bloqueio).")
    				);
    	}
    	
    	if (dataFimBloqueioNegocial.isBefore(LocalDate.now())) {
    		// Erro: Não é possível alterar a data fim do bloqueio para uma data passada.
    		throw new ErroNegocialException(ErrosSistema.ERRO_DATA_FIM_BLOQUEIO_MENOR_QUE_DATA_ATUAL.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_FIM_BLOQUEIO_NEGOCIAL.get(),
    						converteObjectParaString(dataFimBloqueioNegocial))
    				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
    						converteObjectParaString(codigoChaveUsuario))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Alterar Bloqueio Negocial: " +
    						"A data do fim do bloqueio informada é anterior à data atual.")
    				);
    	}
    	
    	return identificacaoAcessoCorrespondente;
    }
    
    public ModelIdentificacaoAcessoCorrespondente validarDadosParaOperacaoExcluir(
    		Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		String codigoChaveUsuario) throws ErroNegocialException {
    	if (codigoInstituicao == null ||
    			codigoIdentificacaoCorrespondente == null ||
    			codigoChaveUsuario == null) {
    		// Erro: campos obrigatórios não preenchidos.
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_REQUISICAO_SEM_VALORES.get()
					.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
							converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
    						converteObjectParaString(codigoChaveUsuario))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Excluir Bloqueio Negocial: " +
    						"Campos obrigatórios não foram preenchidos.")
					);
    	}
    	
    	ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente = 
				identificacaoAcessoCorrespondenteDao.consultarIdentificacaoAcessoCorrespondente(
						codigoInstituicao, codigoIdentificacaoCorrespondente);
    	
    	if (identificacaoAcessoCorrespondente == null) {
    		// Erro: Chave J não localizada.
    		throw new ErroNegocialException(ErrosSistema.ERRO_CODIGO_IDENTIFICACAO_CORRESPONDENTE_NAO_LOCALIZADO.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
    						converteObjectParaString(codigoChaveUsuario))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Excluir Bloqueio Negocial: " +
    						"A Chave J informada não existe na tabela DB2CBO.IDFC_ACSS_CRS. " +
    	    				"A Chave J informada pode não existir ou não ser uma Chave J de Operador de Correspondente.")
    				);
    	}
    	
    	if (!possuiBloqueioAtivo(identificacaoAcessoCorrespondente)) {
    		// Erro: Chave J não possui bloqueio ativo para exclusão.
    		throw new ErroNegocialException(ErrosSistema.ERRO_EXCLUIR_BLOQUEIO_NAO_ATIVO.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						converteObjectParaString(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(codigoIdentificacaoCorrespondente))
    				.addVariavel(ChavesMonitoradasUsuarioLogado.CODIGO_CHAVE_USUARIO_LOGADO.get(),
    						converteObjectParaString(codigoChaveUsuario))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Excluir Bloqueio Negocial: " +
    						"A Chave J informada não possui bloqueio ativo na tabela DB2CBO.IDFC_ACSS_CRS.")
    				);
    	}
    	
    	return identificacaoAcessoCorrespondente;
    }
    
    /* A partir de uma Chave J de Operador de Correspondente, obtém dados de outras tabelas, como o Nome do Correspondente Bancário
     * ao qual está vinculada a chave, dados do Gestor de Rede do Correspondente Bancário à época do bloqueio (inclusão na Lista
     * Restritiva) e dados do operador da Chave J (MCI, CPF, mome). 
     */
    private ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar obterDadosOperadorECorrespondenteBancarioEGestorRede(
    		ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente, LocalDate dataBloqueio)
    				throws ErroNegocialException {
    	ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar bloqueioNegocial =
    			new ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar();
    	
    	bloqueioNegocial.setCodigoInstituicao(identificacaoAcessoCorrespondente.getCodigoInstituicao());
    	
    	bloqueioNegocial.setCodigoIdentificacaoCorrespondente(identificacaoAcessoCorrespondente.getCodigoIdentificacaoCorrespondente());
    	
    	bloqueioNegocial.setCodigoCorrespondente(identificacaoAcessoCorrespondente.getCodigoCorrespondente());
    	
    	bloqueioNegocial.setCodigoOperadorCorrespondente(identificacaoAcessoCorrespondente.getCodigoOperadorCorrespondente());
    	
    	// Recupera os dados do Correspondente Bancário na tabela Correspondente Bancário (DB2CBO.CRS_BCRO).
    	ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio correspondenteBancario = 
    			correspondenteBancarioService.consultarCorrespondenteBancarioParaOperacoesCRUDBloqueioNegocialOperador(
    					identificacaoAcessoCorrespondente.getCodigoInstituicao(),
    					identificacaoAcessoCorrespondente.getCodigoCorrespondente());
    	
    	if (correspondenteBancario == null) {
    		// Erro na recuperação dos dados da tabela Correspondente Bancário.
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_RECUPERACAO_DADOS_BASE_DADOS.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						converteObjectParaString(identificacaoAcessoCorrespondente.getCodigoInstituicao()))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(identificacaoAcessoCorrespondente.getCodigoIdentificacaoCorrespondente()))
    				.addVariavel(ChavesMonitoradasCorrespondenteBancario.CODIGO_CORRESPONDENTE.get(),
    						converteObjectParaString(identificacaoAcessoCorrespondente.getCodigoCorrespondente()))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro ao recuperar os dados do Correspondente Bancário na tabela DB2CBO.CRS_BCRO. " +
    						"Pode ser um problema de conexão com o banco de dados ou problema de integridade " +
    						"(o registro não existe na tabela).")
    				);
    	}
    	
    	if (correspondenteBancario.getNomeCorrespondente() != null && !correspondenteBancario.getNomeCorrespondente().isBlank()) {
    		bloqueioNegocial.setNomeCorrespondente(correspondenteBancario.getNomeCorrespondente());
    	} else {
    		// Configura o Nome do Correspondente com um valor padrão, caso esteja em branco na tabela.
    		bloqueioNegocial.setNomeCorrespondente(CAMPO_TEXTO_DADOS_NAO_LOCALIZADOS);
    	}
    	
    	// Recupera os dados do Gestor de Rede do Correspondente Bancário (Código do Gestor de Rede vinculado ao Correspondente à
    	// época do bloqueio).
    	ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio historicoCorrespondente = 
    			correspondenteBancarioService.consultarHistoricoCorrespondenteBancarioParaOperacoesCRUDBloqueioNegocialOperador(
    					identificacaoAcessoCorrespondente.getCodigoInstituicao(),
    					identificacaoAcessoCorrespondente.getCodigoCorrespondente(), dataBloqueio);
    	
    	if (historicoCorrespondente == null) {
    		// Erro na recuperação dos dados da tabela Histórico Correspondente Bancário.
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_RECUPERACAO_DADOS_BASE_DADOS.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						converteObjectParaString(identificacaoAcessoCorrespondente.getCodigoInstituicao()))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(identificacaoAcessoCorrespondente.getCodigoIdentificacaoCorrespondente()))
    				.addVariavel(ChavesMonitoradasCorrespondenteBancario.CODIGO_CORRESPONDENTE.get(),
    						converteObjectParaString(identificacaoAcessoCorrespondente.getCodigoCorrespondente()))
    				.addVariavel(ChavesMonitoradasHistoricoCorrespondenteBancario.DATA_PESQUISADA.get(),
    						converteObjectParaString(dataBloqueio))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro ao recuperar os dados do Histórico do Correspondente Bancário na tabela DB2CBO.HST_CRS_BCRO. " +
    						"Pode ser um problema de conexão com o banco de dados ou problema de integridade " +
    						"(o registro não existe na tabela).")
    				);
    	}
    	
    	bloqueioNegocial.setCodigoGestorRedeCorrespondente(historicoCorrespondente.getCodigoGestorRedeCorrespondente());
    	
    	String nomeGestorRede;
    	
    	if (historicoCorrespondente.getCodigoGestorRedeCorrespondente() != 0) {
    	
	    	// Recupera o nome do Gestor de Rede do Correspondente Bancário.
	    	ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio gestorRede = 
	    			gestorRedeCorrespondenteBancarioService.consultarGestorRedeCorrespondenteBancarioOperacoesCRUDBloqueioNegocialOperador(
	    					historicoCorrespondente.getCodigoInstituicao(),
	    					historicoCorrespondente.getCodigoGestorRedeCorrespondente());
	    	
	    	if (gestorRede == null) {
	    		// Erro na recuperação dos dados das tabelas Gestor de Rede do Correspondente Bancário e
	    		// Histórico do Gestor de Rede.
	    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_RECUPERACAO_DADOS_BASE_DADOS.get()
	    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
	    						converteObjectParaString(identificacaoAcessoCorrespondente.getCodigoInstituicao()))
	    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
	    						converteObjectParaString(identificacaoAcessoCorrespondente.getCodigoIdentificacaoCorrespondente()))
	    				.addVariavel(ChavesMonitoradasCorrespondenteBancario.CODIGO_CORRESPONDENTE.get(),
	    						converteObjectParaString(identificacaoAcessoCorrespondente.getCodigoCorrespondente()))
	    				.addVariavel(ChavesMonitoradasGestorRede.CODIGO_GESTOR_REDE.get(),
	    						converteObjectParaString(historicoCorrespondente.getCodigoGestorRedeCorrespondente()))
	    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
	    						"Erro ao recuperar os dados do Gestor de Rede na tabela DB2CBO.GST_REDE_CRS_BCRO. " +
	    						"Pode ser um problema de conexão com o banco de dados ou problema de integridade " +
	    						"(o registro não existe na tabela).")
	    				);
	    	}
	    	
	    	if (gestorRede.getNomeGestorRede() != null && !gestorRede.getNomeGestorRede().isBlank()) {
	    		nomeGestorRede = gestorRede.getNomeGestorRede();
	    	} else {
	    		// Configura o Nome do Gestor de Rede com um valor padrão, caso esteja em branco na tabela.
	    		nomeGestorRede = CAMPO_TEXTO_DADOS_NAO_LOCALIZADOS;
	    	}
    	} else {
    		nomeGestorRede = CAMPO_TEXTO_SEM_GESTOR_REDE;
    	}
    	
    	bloqueioNegocial.setNomeGestorRede(nomeGestorRede);
    	
    	// Recupera os dados do Operador (CPF, nome), a partir do código MCI.
    	// Implementado, a partir do consumo da operação IIB 207827.1
    	recuperarDadosMCI(bloqueioNegocial);
    	
    	return bloqueioNegocial;
    }
    
    private ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar montarObjetoRetorno(
    		ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente) throws ErroNegocialException {

    	ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar bloqueioNegocialParaDadosSaidaOperacaoConsultar = null;

    	// Consulta a Lista Restritiva para verificar se há bloqueio ativo para o Código da Identificação do Correspondente,
    	// ou seja, verifica se há bloqueio para a Chave J passada como um dos atributos do objeto parâmetro deste método
    	// (ModelIdentificacaoAcessoCorrespondente identificacaoAcessoCorrespondente).

    	if (identificacaoAcessoCorrespondente != null) {
    		// Obtém os dados do Correspondente e Gestor de Rede que não estão na tabela Identificação de Acesso do Correspondente.
    		//
    		bloqueioNegocialParaDadosSaidaOperacaoConsultar = 
    				obterDadosOperadorECorrespondenteBancarioEGestorRede(
    						identificacaoAcessoCorrespondente, identificacaoAcessoCorrespondente.getDataInicioBloqueioNegocial());

    		bloqueioNegocialParaDadosSaidaOperacaoConsultar.setCodigoTipoBloqueioNegocial(
    				identificacaoAcessoCorrespondente.getCodigoTipoBloqueioNegocial());

    		bloqueioNegocialParaDadosSaidaOperacaoConsultar.setTextoTipoBloqueioNegocial(
    				TEXTOS_BLOQUEIO_NEGOCIAL_OPERADOR[identificacaoAcessoCorrespondente.getCodigoTipoBloqueioNegocial()]);

    		bloqueioNegocialParaDadosSaidaOperacaoConsultar.setDataInicioBloqueioNegocial(
    				identificacaoAcessoCorrespondente.getDataInicioBloqueioNegocial());

    		bloqueioNegocialParaDadosSaidaOperacaoConsultar.setDataFimBloqueioNegocial(
    				identificacaoAcessoCorrespondente.getDataFimBloqueioNegocial());

    		bloqueioNegocialParaDadosSaidaOperacaoConsultar.setTextoJustificativaBloqueioNegocial(
    				identificacaoAcessoCorrespondente.getTextoJustificativaBloqueioNegocial());
    		
//    		bloqueioNegocialParaDadosSaidaOperacaoConsultar.setCodigoChaveUsuario(
//    				identificacaoAcessoCorrespondente.getCodigoChaveUsuario());
//
//    		bloqueioNegocialParaDadosSaidaOperacaoConsultar.setTimestampBloqueioNegocial(
//    				Timestamp.valueOf(LocalDateTime.of(
//    						identificacaoAcessoCorrespondente.getDataAlteracao(),
//    						identificacaoAcessoCorrespondente.getHoraAlteracao())));
    		
    		recuperarTimestampBloqueioNegocialECodigoChaveUsuario(bloqueioNegocialParaDadosSaidaOperacaoConsultar);
    	}

    	return bloqueioNegocialParaDadosSaidaOperacaoConsultar;
    }
    
    /* A partir de uma Chave J de Operador de Correspondente com bloqueio vigente, recuperam-se o timestamp e a chave do usuário
     * responsável pela inclusão do bloqueio. Tais dados são obtidos da tabela DB2CBO.HST_IDFC_ACSS_CRS, já que a tabela
     * DB2CBO.IDFC_ACSS_CRS guarda as informações referentes à última alteração e que, não necessariamente, corresponde
     * à inclusão do bloqueio, já que o bloqueio pode ter sido alterado.
     */
    public void recuperarTimestampBloqueioNegocialECodigoChaveUsuario(
    		ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar bloqueioNegocialParaDadosSaidaOperacaoConsultar)
    				throws ErroNegocialException {
    	try {
    		
    		// Para um bloqueio de Chave J vigente, listam-se todos os históricos de bloqueio dessa Chave J, na data de início
    		// do bloqueio.
    		List<ModelHistoricoIdentificacaoAcessoCorrespondente> listaHistoricoIdentificacaoAcessoCorrespondente =
    				historicoIdentificacaoAcessoCorrespondenteDao.listarHistoricoIdentificacaoAcessoCorrespondentePorDataInicioBloqueio(
    	    				bloqueioNegocialParaDadosSaidaOperacaoConsultar.getCodigoInstituicao(),
    	    				bloqueioNegocialParaDadosSaidaOperacaoConsultar.getCodigoIdentificacaoCorrespondente(),
    	    				bloqueioNegocialParaDadosSaidaOperacaoConsultar.getDataInicioBloqueioNegocial());
			
			// Se não há nenhum histórico da Chave J na tabela de histórico, lança-se um erro negocial.
			if ((listaHistoricoIdentificacaoAcessoCorrespondente == null) ||
					(listaHistoricoIdentificacaoAcessoCorrespondente.size() == 0)) {
    			throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_RECUPERACAO_DADOS_BASE_DADOS.get()
						.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
								converteObjectParaString(bloqueioNegocialParaDadosSaidaOperacaoConsultar.getCodigoInstituicao()))
	    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_OPERADOR_CORRESPONDENTE.get(),
	    						converteObjectParaString(
	    								bloqueioNegocialParaDadosSaidaOperacaoConsultar.getCodigoIdentificacaoCorrespondente()))
	    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_INICIO_BLOQUEIO_NEGOCIAL.get(),
	    						converteObjectParaString(
	    								bloqueioNegocialParaDadosSaidaOperacaoConsultar.getDataInicioBloqueioNegocial()))
	    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
	    						"Erro na Op. Consultar Bloqueio Negocial: " +
	    						"Houve algum erro de Banco de Dados ao recuperar timestamp e responsável pelo " +
	    						"bloqueio na tabela DB2CBO.HST_IDFC_ACSS_CRS.")
						);
    		}
			
			int count = 0;
			
			// O for a seguir é responsável por iterar até a ocorrência de histórico que corresponde à inclusão de
			// bloqueio para o bloqueio vigente da Chave J pesquisada.
			while (count < listaHistoricoIdentificacaoAcessoCorrespondente.size() &&
					listaHistoricoIdentificacaoAcessoCorrespondente.get(count).getCodigoEstadoAtividadeBloqueio() != CODIGO_CANCELADO) {
				count++;
			}
			
			// Como o contador da variável count especifica o índice de um bloqueio CANCELADO e deseja-se
			// retornar os dados do último bloqueio ATIVO, seleciona-se a ocorrência anterior.
			ModelHistoricoIdentificacaoAcessoCorrespondente historicoIdentificacaoAcessoCorrespondente =
					listaHistoricoIdentificacaoAcessoCorrespondente.get(count - 1);
			
			// Retornam-se os dados desejados: Timestamp e Código da Chave do Usuário responsável pela inclusão do bloqueio.
			bloqueioNegocialParaDadosSaidaOperacaoConsultar.setCodigoChaveUsuario(
					historicoIdentificacaoAcessoCorrespondente.getCodigoChaveUsuario());

    		bloqueioNegocialParaDadosSaidaOperacaoConsultar.setTimestampBloqueioNegocial(
    				Timestamp.valueOf(LocalDateTime.of(
    						historicoIdentificacaoAcessoCorrespondente.getDataAlteracao(),
    						historicoIdentificacaoAcessoCorrespondente.getHoraAlteracao())));
			
    	} catch (ErroSqlException e) {
			// Erro de banco de dados.
			throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
							converteObjectParaString(bloqueioNegocialParaDadosSaidaOperacaoConsultar.getCodigoInstituicao()))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_IDENTIFICACAO_CORRESPONDENTE.get(),
    						converteObjectParaString(
    								bloqueioNegocialParaDadosSaidaOperacaoConsultar.getCodigoIdentificacaoCorrespondente()))
    				.addVariavel(ChavesMonitoradasDadosBloqueioOperadorCorrespondente.DATA_INICIO_BLOQUEIO_NEGOCIAL.get(),
    						converteObjectParaString(
    								bloqueioNegocialParaDadosSaidaOperacaoConsultar.getDataInicioBloqueioNegocial()))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro na Op. Consultar Bloqueio Negocial: " +
    						"Houve algum erro de Banco de Dados ao recuperar timestamp e responsável pelo " +
    						"bloqueio na tabela DB2CBO.HST_IDFC_ACSS_CRS.")
					);
		}
    }
    
    /* Recupera os dados do Operador de Correspondente (CPF e nome), a partir do código MCI.
     * Implementado, a partir do consumo da operação IIB 207827.1.
     */
    private void recuperarDadosMCI(ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar bloqueioNegocial) 
    		throws ErroNegocialException {
    	try {
	    	DadosRequisicaoConsultaDadosBasicosCodigoCliente requisicao = new DadosRequisicaoConsultaDadosBasicosCodigoCliente();
	        
	        Entrada entrada = new Entrada();
	        
	        entrada.setCliente(bloqueioNegocial.getCodigoOperadorCorrespondente()); // MCI do Operador do Correspondente
	        
	        requisicao.setEntrada(entrada);
	        
	        DadosRespostaConsultaDadosBasicosCodigoCliente resposta = sidecarConsumidor.executarOperacao(requisicao);
	        
	        if (resposta.getControle().getRetCode() != 0) {
	        	throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_CONSUMO_OPERACAO_IIB.get()
	    				.addVariavel(ChavesMonitoradasConsumoOperacoesIIB.CODIGO_RETORNO_CONSUMO_OPERACAO_IIB.get(),
	    						converteObjectParaString(resposta.getControle().getRetCode()))
	    				.addVariavel(ChavesMonitoradasCliente.MCI_CLIENTE.get(),
	    						converteObjectParaString(bloqueioNegocial.getCodigoOperadorCorrespondente()))
	    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_OPERADOR_CORRESPONDENTE.get(),
	    						converteObjectParaString(bloqueioNegocial.getCodigoOperadorCorrespondente()))
	    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
	    						"Erro no consumo da operação IIB nr. 207827.1 (Consulta Dados Básicos por Código Cliente). " +
	    						"A operação permite recuperar o CPF e nome de um cliente a partir do código MCI.")
	    				);
	        }
	        
	        bloqueioNegocial.setNumeroCpfOperadorCorrespondente(resposta.getSaida().getCpfCgc());
	        bloqueioNegocial.setNomeOperadorCorrespondente(resposta.getSaida().getNome());
    	} catch (CurioNegocioException e){
    		logger.error("=========================================================================================");
    		logger.error("ERRO NO CONSUMO DA OPERAÇÃO IIB NR. 207827.1 (Consulta Dados Básicos por Código Cliente).");
    		logger.error("Cód. do Erro: " + e.getErrors().get(0).getCode());
    		logger.error("Mensagem: " + e.getErrors().get(0).getMessage());
    		logger.error("Sequencial: " + e.getErrors().get(0).getSequential());
    		logger.error("=========================================================================================");
            
            throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_CONSUMO_OPERACAO_IIB.get()
    				.addVariavel(ChavesMonitoradasConsumoOperacoesIIB.CODIGO_ERRO_CONSUMO_OPERACAO_IIB.get(),
    						converteObjectParaString(e.getErrors().get(0).getCode()))
    				.addVariavel(ChavesMonitoradasConsumoOperacoesIIB.MENSAGEM_CONSUMO_OPERACAO_IIB.get(),
    						converteObjectParaString(e.getErrors().get(0).getMessage()))
    				.addVariavel(ChavesMonitoradasConsumoOperacoesIIB.SEQUENCIAL_CONSUMO_OPERACAO_IIB.get(),
    						converteObjectParaString(e.getErrors().get(0).getSequential()))
    				.addVariavel(ChavesMonitoradasCliente.MCI_CLIENTE.get(),
    						converteObjectParaString(bloqueioNegocial.getCodigoOperadorCorrespondente()))
    				.addVariavel(ChavesMonitoradasOperadorCorrespondente.CODIGO_OPERADOR_CORRESPONDENTE.get(),
    						converteObjectParaString(bloqueioNegocial.getCodigoOperadorCorrespondente()))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Erro no consumo da operação IIB nr. 207827.1 (Consulta Dados Básicos por Código Cliente). " +
    						"A operação permite recuperar o CPF e nome de um cliente a partir do código MCI.")
    				);
        }
    }
}
