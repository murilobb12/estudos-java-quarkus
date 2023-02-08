package br.com.bb.cbo.identificacaoAcessoCorrespondente;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar {
	
	private Integer codigoInstituicao;

	private String codigoIdentificacaoCorrespondente; // Chave J
	
	private Integer codigoTipoBloqueioNegocial; // 1 - Práticas não sustentáveis, 2 - indícios de Golpe/fraude, 3 - Outros

	private String textoTipoBloqueioNegocial; // TAM 50. 1 - Práticas não sustentáveis, 2 - indícios de Golpe/fraude, 3 - Outros
	
	private String codigoChaveUsuario;
	
	private Timestamp timestampBloqueioNegocial;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate dataInicioBloqueioNegocial;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDate dataFimBloqueioNegocial;

	private String textoJustificativaBloqueioNegocial; // TAM 500
	
	private Integer codigoOperadorCorrespondente; // MCI do operador da chave J
	
	private Long numeroCpfOperadorCorrespondente; // CPF do operador da chave J
	
	private String nomeOperadorCorrespondente; // Nome do operador da chave J (TAM 60)
	
	private Integer codigoCorrespondente; // MCI do Correspondente Bancário
	
	private String nomeCorrespondente; // Nome do Correspondente Bancário (TAM 30)
	
	private Integer codigoGestorRedeCorrespondente; // Código do Gestor de Rede
	
	private String nomeGestorRede; // Nome do Gestor de Rede (TAM 30)

	public ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar() {}

	public ModelBloqueioNegocialParaDadosSaidaOperacaoConsultar(Integer codigoInstituicao,
			String codigoIdentificacaoCorrespondente, Integer codigoTipoBloqueioNegocial,
			String textoTipoBloqueioNegocial, String codigoChaveUsuario, Timestamp timestampBloqueioNegocial,
			LocalDate dataInicioBloqueioNegocial, LocalDate dataFimBloqueioNegocial,
			String textoJustificativaBloqueioNegocial, Integer codigoOperadorCorrespondente,
			Long numeroCpfOperadorCorrespondente, String nomeOperadorCorrespondente, Integer codigoCorrespondente,
			String nomeCorrespondente, Integer codigoGestorRedeCorrespondente, String nomeGestorRede) {
		this.codigoInstituicao = codigoInstituicao;
		this.codigoIdentificacaoCorrespondente = codigoIdentificacaoCorrespondente;
		this.codigoTipoBloqueioNegocial = codigoTipoBloqueioNegocial;
		this.textoTipoBloqueioNegocial = textoTipoBloqueioNegocial;
		this.codigoChaveUsuario = codigoChaveUsuario;
		
		if (timestampBloqueioNegocial != null) {
			this.timestampBloqueioNegocial = new Timestamp(timestampBloqueioNegocial.getTime());
		} else {
			this.timestampBloqueioNegocial = null;
		}
		
		this.dataInicioBloqueioNegocial = dataInicioBloqueioNegocial;
		this.dataFimBloqueioNegocial = dataFimBloqueioNegocial;
		this.textoJustificativaBloqueioNegocial = textoJustificativaBloqueioNegocial;
		this.codigoOperadorCorrespondente = codigoOperadorCorrespondente;
		this.numeroCpfOperadorCorrespondente = numeroCpfOperadorCorrespondente;
		this.nomeOperadorCorrespondente = nomeOperadorCorrespondente;
		this.codigoCorrespondente = codigoCorrespondente;
		this.nomeCorrespondente = nomeCorrespondente;
		this.codigoGestorRedeCorrespondente = codigoGestorRedeCorrespondente;
		this.nomeGestorRede = nomeGestorRede;
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

	public Integer getCodigoTipoBloqueioNegocial() {
		return codigoTipoBloqueioNegocial;
	}

	public void setCodigoTipoBloqueioNegocial(Integer codigoTipoBloqueioNegocial) {
		this.codigoTipoBloqueioNegocial = codigoTipoBloqueioNegocial;
	}

	public String getTextoTipoBloqueioNegocial() {
		return textoTipoBloqueioNegocial;
	}

	public void setTextoTipoBloqueioNegocial(String textoTipoBloqueioNegocial) {
		this.textoTipoBloqueioNegocial = textoTipoBloqueioNegocial;
	}

	public String getCodigoChaveUsuario() {
		return codigoChaveUsuario;
	}

	public void setCodigoChaveUsuario(String codigoChaveUsuario) {
		this.codigoChaveUsuario = codigoChaveUsuario;
	}

	public Timestamp getTimestampBloqueioNegocial() {
		if (timestampBloqueioNegocial != null) {
			return new Timestamp(timestampBloqueioNegocial.getTime());
		} else {
			return null;
		}
	}

	public void setTimestampBloqueioNegocial(Timestamp timestampBloqueioNegocial) {
		if (timestampBloqueioNegocial != null) {
			this.timestampBloqueioNegocial = new Timestamp(timestampBloqueioNegocial.getTime());
		} else {
			this.timestampBloqueioNegocial = null;
		}
		
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

	public String getTextoJustificativaBloqueioNegocial() {
		return textoJustificativaBloqueioNegocial;
	}

	public void setTextoJustificativaBloqueioNegocial(String textoJustificativaBloqueioNegocial) {
		this.textoJustificativaBloqueioNegocial = textoJustificativaBloqueioNegocial;
	}

	public Integer getCodigoOperadorCorrespondente() {
		return codigoOperadorCorrespondente;
	}

	public void setCodigoOperadorCorrespondente(Integer codigoOperadorCorrespondente) {
		this.codigoOperadorCorrespondente = codigoOperadorCorrespondente;
	}

	public Long getNumeroCpfOperadorCorrespondente() {
		return numeroCpfOperadorCorrespondente;
	}

	public void setNumeroCpfOperadorCorrespondente(Long numeroCpfOperadorCorrespondente) {
		this.numeroCpfOperadorCorrespondente = numeroCpfOperadorCorrespondente;
	}

	public String getNomeOperadorCorrespondente() {
		return nomeOperadorCorrespondente;
	}

	public void setNomeOperadorCorrespondente(String nomeOperadorCorrespondente) {
		this.nomeOperadorCorrespondente = nomeOperadorCorrespondente;
	}

	public Integer getCodigoCorrespondente() {
		return codigoCorrespondente;
	}

	public void setCodigoCorrespondente(Integer codigoCorrespondente) {
		this.codigoCorrespondente = codigoCorrespondente;
	}

	public String getNomeCorrespondente() {
		return nomeCorrespondente;
	}

	public void setNomeCorrespondente(String nomeCorrespondente) {
		this.nomeCorrespondente = nomeCorrespondente;
	}

	public Integer getCodigoGestorRedeCorrespondente() {
		return codigoGestorRedeCorrespondente;
	}

	public void setCodigoGestorRedeCorrespondente(Integer codigoGestorRedeCorrespondente) {
		this.codigoGestorRedeCorrespondente = codigoGestorRedeCorrespondente;
	}

	public String getNomeGestorRede() {
		return nomeGestorRede;
	}

	public void setNomeGestorRede(String nomeGestorRede) {
		this.nomeGestorRede = nomeGestorRede;
	}
}
