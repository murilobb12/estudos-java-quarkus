package br.com.bb.cbo.exceptions;

import br.com.bb.dev.ext.error.ChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;

public enum ChavesMonitoradasHistoricoCorrespondenteBancario implements IEnumChaveMonitorada {
	DATA_PESQUISADA("DATA-PESQUISADA", 10),
	;

	IChaveMonitorada chaveMonitorada;

	ChavesMonitoradasHistoricoCorrespondenteBancario(String chave, int tamanho) {
		chaveMonitorada = new ChaveMonitorada(chave, tamanho);
	}

	@Override
	public IChaveMonitorada get() {
		return chaveMonitorada;
	}
}
