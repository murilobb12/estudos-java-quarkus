package br.com.bb.cbo.exceptions;

import br.com.bb.dev.ext.error.ChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;

public enum ChavesMonitoradasUsuarioLogado implements IEnumChaveMonitorada {
	CODIGO_CHAVE_USUARIO_LOGADO("CHAVE-USUARIO-LOGADO", 8),
	;

	IChaveMonitorada chaveMonitorada;

	ChavesMonitoradasUsuarioLogado(String chave, int tamanho) {
		chaveMonitorada = new ChaveMonitorada(chave, tamanho);
	}

	@Override
	public IChaveMonitorada get() {
		return chaveMonitorada;
	}
}
