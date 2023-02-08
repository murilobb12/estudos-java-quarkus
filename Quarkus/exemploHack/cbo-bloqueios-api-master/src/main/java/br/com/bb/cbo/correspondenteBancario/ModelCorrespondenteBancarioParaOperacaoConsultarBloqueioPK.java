package br.com.bb.cbo.correspondenteBancario;

import java.io.Serializable;

public class ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK implements Serializable {

	private static final long serialVersionUID = 4249985684995381686L;

	private int codigoInstituicao;
	
	private int codigoCorrespondente;
	
	public ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK() {}

	public ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK(int codigoInstituicao,
			int codigoCorrespondente) {
		this.codigoInstituicao = codigoInstituicao;
		this.codigoCorrespondente = codigoCorrespondente;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCodigoInstituicao() {
		return codigoInstituicao;
	}

	public void setCodigoInstituicao(int codigoInstituicao) {
		this.codigoInstituicao = codigoInstituicao;
	}

	public int getCodigoCorrespondente() {
		return codigoCorrespondente;
	}

	public void setCodigoCorrespondente(int codigoCorrespondente) {
		this.codigoCorrespondente = codigoCorrespondente;
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
