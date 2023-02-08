package br.com.bb.cbo.identificacaoAcessoCorrespondente;

import java.sql.Timestamp;
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
@IdClass(ModelHistoricoIdentificacaoAcessoCorrespondentePK.class)
@NamedNativeQueries({
	@NamedNativeQuery(
			name = "LISTAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE_POR_DATA_INICIO_BLOQUEIO_NEGOCIAL_ORDER_BY_TIMESTAMP_DESC",
			query = "SELECT CD_INST, CD_IDFC_CRS, TS_ATL_HST, CD_EST_ATVD, CD_CRS, NR_ULT_SEQL_IDFC, " + 
					"CD_CHV_USU, DT_ALT, HR_ALT, CD_OPRD_CRS, CD_ACSS_PRVR_OPRD, CD_LOJA_CRS, " + 
					"DT_INC_BLQ_NEGL, DT_FIM_BLQ_NEGL, CD_EST_ATVD_BLQ, CD_TIP_BLQ_NEGL, TX_JST_BLQ_NEGL " + 
					"FROM DB2CBO.HST_IDFC_ACSS_CRS " + 
					"WHERE CD_INST = :codigoInstituicao " + 
					"AND UPPER(CD_IDFC_CRS) = UPPER(:codigoIdentificacaoCorrespondente) " +
					"AND DT_INC_BLQ_NEGL = :dataInicioBloqueioNegocial " +
					"ORDER BY TS_ATL_HST DESC",
					resultClass = ModelHistoricoIdentificacaoAcessoCorrespondente.class),
	@NamedNativeQuery(
			name = "INCLUIR_HISTORICO_IDENTIFICACAO_ACESSO_CORRESPONDENTE",
			query = "INSERT INTO DB2CBO.HST_IDFC_ACSS_CRS " +
					"(CD_INST, CD_IDFC_CRS, TS_ATL_HST, CD_EST_ATVD, CD_CRS, NR_ULT_SEQL_IDFC, " +
					"CD_CHV_USU, DT_ALT, HR_ALT, CD_OPRD_CRS, CD_ACSS_PRVR_OPRD, CD_LOJA_CRS, " +
					"DT_INC_BLQ_NEGL, DT_FIM_BLQ_NEGL, CD_EST_ATVD_BLQ, " +
					"CD_TIP_BLQ_NEGL, TX_JST_BLQ_NEGL) " +
					"VALUES (:codigoInstituicao, :codigoIdentificacaoCorrespondente, " +
					":timestampAtualizacaoHistorico, :codigoEstadoAtividade, " +
					":codigoCorrespondente, :numeroUltimoSequencialIdentificacao, " +
					":codigoChaveUsuario, :dataAlteracao, " +
					":horaAlteracao, :codigoOperadorCorrespondente, " +
					":codigoAcessoProvisorioOperador, :codigoLojaCorrespondente, " +
					":dataInicioBloqueioNegocial, :dataFimBloqueioNegocial, " +
					":codigoEstadoAtividadeBloqueio, :codigoTipoBloqueioNegocial, " +
					":textoJustificativaBloqueioNegocial)",
			resultClass = ModelHistoricoIdentificacaoAcessoCorrespondente.class)
})
public class ModelHistoricoIdentificacaoAcessoCorrespondente {

	@Id
	@Column(name = "CD_INST")
	private Integer codigoInstituicao;
	
	@Id
	@Column(name = "CD_IDFC_CRS")
	private String codigoIdentificacaoCorrespondente;
	
	@Id
	@Column(name = "TS_ATL_HST")
	private Timestamp timestampAtualizacaoHistorico;
	
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

	public ModelHistoricoIdentificacaoAcessoCorrespondente() {}

	public ModelHistoricoIdentificacaoAcessoCorrespondente(Integer codigoInstituicao,
			String codigoIdentificacaoCorrespondente, Timestamp timestampAtualizacaoHistorico,
			Integer codigoEstadoAtividade, Integer codigoCorrespondente, Integer numeroUltimoSequencialIdentificacao,
			String codigoChaveUsuario, LocalDate dataAlteracao, LocalTime horaAlteracao,
			Integer codigoOperadorCorrespondente, Integer codigoAcessoProvisorioOperador,
			Integer codigoLojaCorrespondente, LocalDate dataInicioBloqueioNegocial, LocalDate dataFimBloqueioNegocial,
			Integer codigoEstadoAtividadeBloqueio, Integer codigoTipoBloqueioNegocial,
			String textoJustificativaBloqueioNegocial) {
		this.codigoInstituicao = codigoInstituicao;
		this.codigoIdentificacaoCorrespondente = codigoIdentificacaoCorrespondente;
		
		if (timestampAtualizacaoHistorico != null) {
			this.timestampAtualizacaoHistorico = new Timestamp(timestampAtualizacaoHistorico.getTime());
		} else {
			this.timestampAtualizacaoHistorico = null;
		}
		
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

	public Timestamp getTimestampAtualizacaoHistorico() {
		if (this.timestampAtualizacaoHistorico != null) {
			return new Timestamp(this.timestampAtualizacaoHistorico.getTime());
		} else {
			return null;
		}
	}

	public void setTimestampAtualizacaoHistorico(Timestamp timestampAtualizacaoHistorico) {
		if (timestampAtualizacaoHistorico != null) {
			this.timestampAtualizacaoHistorico = new Timestamp(timestampAtualizacaoHistorico.getTime());
		} else {
			this.timestampAtualizacaoHistorico = null;
		}
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
