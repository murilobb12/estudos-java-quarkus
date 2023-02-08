package br.com.bb.cbo.gestorRedeCorrespondenteBancario;

import java.io.Serializable;

public class ModelGestorRedeCorrespondenteBancarioPK implements Serializable {

	private static final long serialVersionUID = 7187215866389299308L;

	private int codigoInstituicao;
	
	private int codigoGestorRedeCorrespondente;
	
	public ModelGestorRedeCorrespondenteBancarioPK() {}

	public ModelGestorRedeCorrespondenteBancarioPK(int codigoInstituicao, int codigoGestorRedeCorrespondente) {
		this.codigoInstituicao = codigoInstituicao;
		this.codigoGestorRedeCorrespondente = codigoGestorRedeCorrespondente;
	}

	public int getCodigoInstituicao() {
		return codigoInstituicao;
	}

	public void setCodigoInstituicao(int codigoInstituicao) {
		this.codigoInstituicao = codigoInstituicao;
	}

	public int getCodigoGestorRedeCorrespondente() {
		return codigoGestorRedeCorrespondente;
	}

	public void setCodigoGestorRedeCorrespondente(int codigoGestorRedeCorrespondente) {
		this.codigoGestorRedeCorrespondente = codigoGestorRedeCorrespondente;
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
