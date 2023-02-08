package br.com.bb.cbo.identificacaoAcessoCorrespondente;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

@Entity
@IdClass(ModelIdentificacaoAcessoCorrespondentePK.class)
@NamedNativeQueries({
	@NamedNativeQuery(name = "ALTERAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE_PARA_OPERACAO_INCLUIR_BLOQUEIO",
			query = "UPDATE DB2CBO.IDFC_ACSS_CRS " +
					"SET CD_TIP_BLQ_NEGL = :codigoTipoBloqueioNegocial, " +
					"CD_CHV_USU = :codigoChaveUsuario, " +
					"DT_ALT = :dataAlteracao, " +
					"HR_ALT = :horaAlteracao, " +
					"DT_INC_BLQ_NEGL = :dataInicioBloqueioNegocial, " +
					"DT_FIM_BLQ_NEGL = :dataFimBloqueioNegocial, " +
					"CD_EST_ATVD_BLQ = :codigoEstadoAtividadeBloqueio, " +
					"TX_JST_BLQ_NEGL = :textoJustificativaBloqueioNegocial " +
					"WHERE CD_INST = :codigoInstituicao " +
					"AND UPPER(CD_IDFC_CRS) = UPPER(:codigoIdentificacaoCorrespondente)"),
	@NamedNativeQuery(name = "ALTERAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE_PARA_OPERACAO_ALTERAR_BLOQUEIO",
			query = "UPDATE DB2CBO.IDFC_ACSS_CRS " +
					"SET CD_CHV_USU = :codigoChaveUsuario, " +
					"DT_ALT = :dataAlteracao, " +
					"HR_ALT = :horaAlteracao, " +
					"DT_FIM_BLQ_NEGL = :dataFimBloqueioNegocial " +
					"WHERE CD_INST = :codigoInstituicao " +
					"AND UPPER(CD_IDFC_CRS) = UPPER(:codigoIdentificacaoCorrespondente)"),
	@NamedNativeQuery(name = "ALTERAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE_PARA_OPERACAO_EXCLUIR_BLOQUEIO",
			query = "UPDATE DB2CBO.IDFC_ACSS_CRS " +
					"SET CD_EST_ATVD_BLQ = :codigoEstadoAtividadeBloqueio, " +
					"CD_CHV_USU = :codigoChaveUsuario, " +
					"DT_ALT = :dataAlteracao, " +
					"HR_ALT = :horaAlteracao " +
					"WHERE CD_INST = :codigoInstituicao " +
					"AND UPPER(CD_IDFC_CRS) = UPPER(:codigoIdentificacaoCorrespondente)"),
	@NamedNativeQuery(
			name = "CONSULTAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE",
			query = "SELECT CD_INST, CD_IDFC_CRS, CD_EST_ATVD, CD_CRS, NR_ULT_SEQL_IDFC, " +
					"CD_CHV_USU, DT_ALT, HR_ALT, CD_OPRD_CRS, CD_ACSS_PRVR_OPRD, CD_LOJA_CRS, " +
					"DT_INC_BLQ_NEGL, DT_FIM_BLQ_NEGL, CD_EST_ATVD_BLQ, " +
					"CD_TIP_BLQ_NEGL, TX_JST_BLQ_NEGL FROM DB2CBO.IDFC_ACSS_CRS " + 
					"WHERE CD_INST = :codigoInstituicao " + 
					"AND UPPER(CD_IDFC_CRS) = UPPER(:codigoIdentificacaoCorrespondente)",
			resultClass = ModelIdentificacaoAcessoCorrespondente.class),
	@NamedNativeQuery(
			name = "LISTAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE",
			query = "SELECT CD_INST, CD_IDFC_CRS, CD_EST_ATVD, CD_CRS, NR_ULT_SEQL_IDFC, " + 
					"CD_CHV_USU, DT_ALT, HR_ALT, CD_OPRD_CRS, CD_ACSS_PRVR_OPRD, CD_LOJA_CRS, " + 
					"DT_INC_BLQ_NEGL, DT_FIM_BLQ_NEGL, CD_EST_ATVD_BLQ, CD_TIP_BLQ_NEGL, TX_JST_BLQ_NEGL " + 
					"FROM (SELECT row_number() OVER (ORDER BY CD_IDFC_CRS) AS REGISTRO, CD_INST, " +
					"CD_IDFC_CRS, CD_EST_ATVD, CD_CRS, NR_ULT_SEQL_IDFC, " + 
					"CD_CHV_USU, DT_ALT, HR_ALT, CD_OPRD_CRS, CD_ACSS_PRVR_OPRD, CD_LOJA_CRS, " + 
					"DT_INC_BLQ_NEGL, DT_FIM_BLQ_NEGL, CD_EST_ATVD_BLQ, CD_TIP_BLQ_NEGL, TX_JST_BLQ_NEGL " + 
					"FROM DB2CBO.IDFC_ACSS_CRS " + 
					"WHERE CD_INST = :codigoInstituicao " + 
					"AND CD_OPRD_CRS = :codigoOperadorCorrespondente " + 
					") WHERE REGISTRO BETWEEN :registroInicial AND :registroFinal",
					resultClass = ModelIdentificacaoAcessoCorrespondente.class)
})
public class ModelIdentificacaoAcessoCorrespondente {

	@Id
	@Column(name = "CD_INST")
	private Integer codigoInstituicao;
	
	@Id
	@Column(name = "CD_IDFC_CRS")
	private String codigoIdentificacaoCorrespondente;
	
	@Column(name = "CD_EST_ATVD")
	private Integer codigoEstadoAtividade;
	
	@Column(name = "CD_CRS")
	private Integer codigoCorrespondente;
	
	@Column(name = "NR_ULT_SEQL_IDFC", nullable=true)
	private Integer numeroUltimoSequencialIdentificacao;
	
	@Column(name = "CD_CHV_USU")
	private String codigoChaveUsuario;
	
	@Column(name = "DT_ALT")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate dataAlteracao;
	
	@Column(name = "HR_ALT")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalTime horaAlteracao;
	
	@Column(name = "CD_OPRD_CRS")
	private Integer codigoOperadorCorrespondente;
	
	@Column(name = "CD_ACSS_PRVR_OPRD")
	private Integer codigoAcessoProvisorioOperador;
	
	@Column(name = "CD_LOJA_CRS")
	private Integer codigoLojaCorrespondente;
	
	@Column(name = "DT_INC_BLQ_NEGL", nullable=true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate dataInicioBloqueioNegocial;

	@Column(name = "DT_FIM_BLQ_NEGL", nullable=true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate dataFimBloqueioNegocial;
	
	@Column(name = "CD_EST_ATVD_BLQ", nullable=true)
	private Integer codigoEstadoAtividadeBloqueio; // 2 - ATIVO, 4 - CANCELADO
	
	@Column(name = "CD_TIP_BLQ_NEGL", nullable=true)
	private Integer codigoTipoBloqueioNegocial; // 1 - Práticas não sustentáveis, 2 - indícios de Golpe/fraude, 3 - Outros

	@Column(name = "TX_JST_BLQ_NEGL", nullable=true)
	private String textoJustificativaBloqueioNegocial; // TAM 500

	public ModelIdentificacaoAcessoCorrespondente() {}

	public ModelIdentificacaoAcessoCorrespondente(Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
			Integer codigoEstadoAtividade, Integer codigoCorrespondente, Integer numeroUltimoSequencialIdentificacao,
			String codigoChaveUsuario, LocalDate dataAlteracao, LocalTime horaAlteracao,
			Integer codigoOperadorCorrespondente, Integer codigoAcessoProvisorioOperador,
			Integer codigoLojaCorrespondente, LocalDate dataInicioBloqueioNegocial, LocalDate dataFimBloqueioNegocial,
			Integer codigoEstadoAtividadeBloqueio, Integer codigoTipoBloqueioNegocial,
			String textoJustificativaBloqueioNegocial) {
		this.codigoInstituicao = codigoInstituicao;
		this.codigoIdentificacaoCorrespondente = codigoIdentificacaoCorrespondente;
		this.codigoEstadoAtividade = codigoEstadoAtividade;
		this.codigoCorrespondente = codigoCorrespondente;
		this.numeroUltimoSequencialIdentificacao = numeroUltimoSequencialIdentificacao;
		this.codigoChaveUsuario = codigoChaveUsuario;
		this.dataAlteracao = dataAlteracao;
		this.horaAlteracao = horaAlteracao;
		this.codigoOperadorCorrespondente = codigoOperadorCorrespondente;
		this.codigoAcessoProvisorioOperador = codigoAcessoProvisorioOperador;
		this.codigoLojaCorrespondente = codigoLojaCorrespondente;
		this.dataInicioBloqueioNegocial = dataInicioBloqueioNegocial;
		this.dataFimBloqueioNegocial = dataFimBloqueioNegocial;
		this.codigoEstadoAtividadeBloqueio = codigoEstadoAtividadeBloqueio;
		this.codigoTipoBloqueioNegocial = codigoTipoBloqueioNegocial;
		this.textoJustificativaBloqueioNegocial = textoJustificativaBloqueioNegocial;
	}

	public Integer getCodigoInstituicao() {
		return codigoInstituicao;
	}

	public void setCodigoInstituicao(Integer codigoInstituicao) {
		this.codigoInstituicao = codigoInstituicao;
	}

	public String getCodigoIdentificacaoCorrespondente() {
		return codigoIdentificacaoCorrespondente;
	}

	public void setCodigoIdentificacaoCorrespondente(String codigoIdentificacaoCorrespondente) {
		this.codigoIdentificacaoCorrespondente = codigoIdentificacaoCorrespondente;
	}

	public Integer getCodigoEstadoAtividade() {
		return codigoEstadoAtividade;
	}

	public void setCodigoEstadoAtividade(Integer codigoEstadoAtividade) {
		this.codigoEstadoAtividade = codigoEstadoAtividade;
	}

	public Integer getCodigoCorrespondente() {
		return codigoCorrespondente;
	}

	public void setCodigoCorrespondente(Integer codigoCorrespondente) {
		this.codigoCorrespondente = codigoCorrespondente;
	}

	public Integer getNumeroUltimoSequencialIdentificacao() {
		return numeroUltimoSequencialIdentificacao;
	}

	public void setNumeroUltimoSequencialIdentificacao(Integer numeroUltimoSequencialIdentificacao) {
		this.numeroUltimoSequencialIdentificacao = numeroUltimoSequencialIdentificacao;
	}

	public String getCodigoChaveUsuario() {
		return codigoChaveUsuario;
	}

	public void setCodigoChaveUsuario(String codigoChaveUsuario) {
		this.codigoChaveUsuario = codigoChaveUsuario;
	}

	public LocalDate getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDate dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public LocalTime getHoraAlteracao() {
		return horaAlteracao;
	}

	public void setHoraAlteracao(LocalTime horaAlteracao) {
		this.horaAlteracao = horaAlteracao;
	}

	public Integer getCodigoOperadorCorrespondente() {
		return codigoOperadorCorrespondente;
	}

	public void setCodigoOperadorCorrespondente(Integer codigoOperadorCorrespondente) {
		this.codigoOperadorCorrespondente = codigoOperadorCorrespondente;
	}

	public Integer getCodigoAcessoProvisorioOperador() {
		return codigoAcessoProvisorioOperador;
	}

	public void setCodigoAcessoProvisorioOperador(Integer codigoAcessoProvisorioOperador) {
		this.codigoAcessoProvisorioOperador = codigoAcessoProvisorioOperador;
	}

	public Integer getCodigoLojaCorrespondente() {
		return codigoLojaCorrespondente;
	}

	public void setCodigoLojaCorrespondente(Integer codigoLojaCorrespondente) {
		this.codigoLojaCorrespondente = codigoLojaCorrespondente;
	}

	public LocalDate getDataInicioBloqueioNegocial() {
		return dataInicioBloqueioNegocial;
	}

	public void setDataInicioBloqueioNegocial(LocalDate dataInicioBloqueioNegocial) {
		this.dataInicioBloqueioNegocial = dataInicioBloqueioNegocial;
	}

	public LocalDate getDataFimBloqueioNegocial() {
		return dataFimBloqueioNegocial;
	}

	public void setDataFimBloqueioNegocial(LocalDate dataFimBloqueioNegocial) {
		this.dataFimBloqueioNegocial = dataFimBloqueioNegocial;
	}

	public Integer getCodigoEstadoAtividadeBloqueio() {
		return codigoEstadoAtividadeBloqueio;
	}

	public void setCodigoEstadoAtividadeBloqueio(Integer codigoEstadoAtividadeBloqueio) {
		this.codigoEstadoAtividadeBloqueio = codigoEstadoAtividadeBloqueio;
	}

	public Integer getCodigoTipoBloqueioNegocial() {
		return codigoTipoBloqueioNegocial;
	}

	public void setCodigoTipoBloqueioNegocial(Integer codigoTipoBloqueioNegocial) {
		this.codigoTipoBloqueioNegocial = codigoTipoBloqueioNegocial;
	}

	public String getTextoJustificativaBloqueioNegocial() {
		return textoJustificativaBloqueioNegocial;
	}

	public void setTextoJustificativaBloqueioNegocial(String textoJustificativaBloqueioNegocial) {
		this.textoJustificativaBloqueioNegocial = textoJustificativaBloqueioNegocial;
	}
}
