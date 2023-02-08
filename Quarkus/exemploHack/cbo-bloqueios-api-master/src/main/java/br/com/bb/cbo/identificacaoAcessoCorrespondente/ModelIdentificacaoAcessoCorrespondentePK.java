package br.com.bb.cbo.identificacaoAcessoCorrespondente;

import java.io.Serializable;

public class ModelIdentificacaoAcessoCorrespondentePK implements Serializable {

	private static final long serialVersionUID = 8862096227831029815L;

	private Integer codigoInstituicao;
	
	private String codigoIdentificacaoCorrespondente;
	
	public ModelIdentificacaoAcessoCorrespondentePK() {}

	public ModelIdentificacaoAcessoCorrespondentePK(Integer codigoInstituicao,
			String codigoIdentificacaoCorrespondente) {
		this.codigoInstituicao = codigoInstituicao;
		this.codigoIdentificacaoCorrespondente = codigoIdentificacaoCorrespondente;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
