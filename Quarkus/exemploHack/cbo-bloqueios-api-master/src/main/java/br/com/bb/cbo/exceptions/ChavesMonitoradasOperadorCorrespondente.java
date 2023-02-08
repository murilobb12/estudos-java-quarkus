package br.com.bb.cbo.exceptions;

import br.com.bb.dev.ext.error.ChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;

public enum ChavesMonitoradasOperadorCorrespondente implements IEnumChaveMonitorada {
	CODIGO_IDENTIFICACAO_CORRESPONDENTE("CHAVE-J-OPERADOR", 8),
	CODIGO_OPERADOR_CORRESPONDENTE("CODIGO-MCI-OPERADOR", 9),
	;

	IChaveMonitorada chaveMonitorada;

	ChavesMonitoradasOperadorCorrespondente(String chave, int tamanho) {
		chaveMonitorada = new ChaveMonitorada(chave, tamanho);
	}

	@Override
	public IChaveMonitorada get() {
		return chaveMonitorada;
	}
}
