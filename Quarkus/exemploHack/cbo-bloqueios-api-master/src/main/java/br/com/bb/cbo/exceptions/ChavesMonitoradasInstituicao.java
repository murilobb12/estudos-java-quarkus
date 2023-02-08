package br.com.bb.cbo.exceptions;

import br.com.bb.dev.ext.error.ChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;

public enum ChavesMonitoradasInstituicao implements IEnumChaveMonitorada {
	CODIGO_INSTITUICAO("CODIGO-INSTITUICAO", 9),
	;

	IChaveMonitorada chaveMonitorada;

	ChavesMonitoradasInstituicao(String chave, int tamanho) {
		chaveMonitorada = new ChaveMonitorada(chave, tamanho);
	}

	@Override
	public IChaveMonitorada get() {
		return chaveMonitorada;
	}
}
