package br.com.bb.cbo.gestorRedeCorrespondenteBancario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@IdClass(ModelGestorRedeCorrespondenteBancarioPK.class)
@NamedNativeQueries({
		@NamedNativeQuery(name = "CONSULTAR_GESTOR_REDE_PARA_OPERACAO_CONSULTAR_BLOQUEIO",
			query = "SELECT CD_INST, CD_GST_REDE_CRS, NM_GST_REDE " + 
	    			"FROM DB2CBO.GST_REDE_CRS_BCRO " + 
	    			"WHERE CD_INST = :codigoInstituicao " + 
	    			"AND CD_GST_REDE_CRS = :codigoGestorRedeCorrespondente", 
			resultClass = ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio.class)
		})
public class ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio {

	@Id
	@Column(name = "CD_INST")
	private int codigoInstituicao;
	
	@Id
	@Column(name = "CD_GST_REDE_CRS")
	private int codigoGestorRedeCorrespondente;
	
	@Column(name = "NM_GST_REDE", nullable=true)
	private String nomeGestorRede;

	public ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio() {}

	public ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio(int codigoInstituicao,
			int codigoGestorRedeCorrespondente, String nomeGestorRede) {
		this.codigoInstituicao = codigoInstituicao;
		this.codigoGestorRedeCorrespondente = codigoGestorRedeCorrespondente;
		this.nomeGestorRede = nomeGestorRede;
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

	public String getNomeGestorRede() {
		return nomeGestorRede;
	}

	public void setNomeGestorRede(String nomeGestorRede) {
		this.nomeGestorRede = nomeGestorRede;
	}
}
