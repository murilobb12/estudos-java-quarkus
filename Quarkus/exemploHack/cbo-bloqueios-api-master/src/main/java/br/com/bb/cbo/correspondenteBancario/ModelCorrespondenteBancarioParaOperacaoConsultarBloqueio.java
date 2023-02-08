package br.com.bb.cbo.correspondenteBancario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@IdClass(ModelCorrespondenteBancarioParaOperacaoConsultarBloqueioPK.class)
@NamedNativeQueries({
		@NamedNativeQuery(name = "CONSULTAR_CORRESPONDENTE_POR_CODIGO_INSTITUICAO_E_CODIGO_CORRESPONDENTE_PARA_OPERACAO_CONSULTAR_BLOQUEIO",
			query = "SELECT CD_INST, CD_CRS, NM_CRS " + 
	    			"FROM DB2CBO.CRS_BCRO " + 
	    			"WHERE CD_INST = :codigoInstituicao " + 
	    			"AND CD_CRS = :codigoCorrespondente", 
			resultClass = ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio.class)
		})
public class ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio {

	@Id
	@Column(name = "CD_INST")
	private int codigoInstituicao;
	
	@Id
	@Column(name = "CD_CRS")
	private int codigoCorrespondente;
	
	@Column(name = "NM_CRS", nullable=true)
	private String nomeCorrespondente;

	public ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio() {}

	public ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio(int codigoInstituicao,
			int codigoCorrespondente, String nomeCorrespondente) {
		this.codigoInstituicao = codigoInstituicao;
		this.codigoCorrespondente = codigoCorrespondente;
		this.nomeCorrespondente = nomeCorrespondente;
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

	public String getNomeCorrespondente() {
		return nomeCorrespondente;
	}

	public void setNomeCorrespondente(String nomeCorrespondente) {
		this.nomeCorrespondente = nomeCorrespondente;
	}
}
