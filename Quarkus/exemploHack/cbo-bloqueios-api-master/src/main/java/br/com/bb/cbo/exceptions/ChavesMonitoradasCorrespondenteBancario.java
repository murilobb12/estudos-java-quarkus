package br.com.bb.cbo.exceptions;

import br.com.bb.dev.ext.error.ChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;

public enum ChavesMonitoradasCorrespondenteBancario implements IEnumChaveMonitorada {
	CODIGO_CORRESPONDENTE("CODI-MCI-CORRESPONDENTE", 9),
	NOME_CORRESPONDENTE("NOME-CORRESPONDENTE", 30),
	;

	IChaveMonitorada chaveMonitorada;

	ChavesMonitoradasCorrespondenteBancario(String chave, int tamanho) {
		chaveMonitorada = new ChaveMonitorada(chave, tamanho);
	}

	@Override
	public IChaveMonitorada get() {
		return chaveMonitorada;
	}
}
