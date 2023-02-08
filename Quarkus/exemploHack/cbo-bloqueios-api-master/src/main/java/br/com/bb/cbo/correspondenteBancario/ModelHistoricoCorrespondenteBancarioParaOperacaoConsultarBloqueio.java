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
		@NamedNativeQuery(name = "CONSULTAR_HISTORICO_CORRESPONDENTE_POR_CODIGO_INSTITUICAO_E_CODIGO_CORRESPONDENTE_E_DATA",
			query = "SELECT CD_INST, CD_CRS, CD_GST_REDE_CRS " + 
	    			"FROM DB2CBO.HST_CRS_BCRO " + 
	    			"WHERE CD_INST = :codigoInstituicao " + 
	    			"AND CD_CRS = :codigoCorrespondente " + 
	    			"AND DT_ALT <= :dataBloqueio " + 
	    			"ORDER BY DT_ALT DESC, HR_ALT DESC " + 
	    			"FETCH FIRST 1 ROW ONLY", 
			resultClass = ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio.class)
		})
public class ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio {

	@Id
	@Column(name = "CD_INST")
	private int codigoInstituicao;
	
	@Id
	@Column(name = "CD_CRS")
	private int codigoCorrespondente;
	
	@Column(name = "CD_GST_REDE_CRS")
	private int codigoGestorRedeCorrespondente;
	
	public ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio() {}

	public ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio(int codigoInstituicao,
			int codigoCorrespondente, int codigoGestorRedeCorrespondente) {
		this.codigoInstituicao = codigoInstituicao;
		this.codigoCorrespondente = codigoCorrespondente;
		this.codigoGestorRedeCorrespondente = codigoGestorRedeCorrespondente;
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

	public int getCodigoGestorRedeCorrespondente() {
		return codigoGestorRedeCorrespondente;
	}

	public void setCodigoGestorRedeCorrespondente(int codigoGestorRedeCorrespondente) {
		this.codigoGestorRedeCorrespondente = codigoGestorRedeCorrespondente;
	}
}
