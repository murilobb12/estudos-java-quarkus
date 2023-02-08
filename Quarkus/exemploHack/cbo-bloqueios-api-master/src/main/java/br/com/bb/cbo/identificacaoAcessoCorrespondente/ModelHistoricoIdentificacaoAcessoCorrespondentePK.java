package br.com.bb.cbo.identificacaoAcessoCorrespondente;

import java.io.Serializable;
import java.sql.Timestamp;

public class ModelHistoricoIdentificacaoAcessoCorrespondentePK implements Serializable {

	private static final long serialVersionUID = 3751576493020166979L;

	private Integer codigoInstituicao;
	
	private String codigoIdentificacaoCorrespondente;
	
	private Timestamp timestampAtualizacaoHistorico;
	
	public ModelHistoricoIdentificacaoAcessoCorrespondentePK() {}

	public ModelHistoricoIdentificacaoAcessoCorrespondentePK(Integer codigoInstituicao,
			String codigoIdentificacaoCorrespondente, Timestamp timestampAtualizacaoHistorico) {
		this.codigoInstituicao = codigoInstituicao;
		this.codigoIdentificacaoCorrespondente = codigoIdentificacaoCorrespondente;
		
		if (timestampAtualizacaoHistorico != null) {
			this.timestampAtualizacaoHistorico = new Timestamp(timestampAtualizacaoHistorico.getTime());
		} else {
			this.timestampAtualizacaoHistorico = null;
		}
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
