package br.com.bb.cbo.exceptions;

import br.com.bb.dev.ext.error.ChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;

public enum ChavesMonitoradasGestorRede implements IEnumChaveMonitorada {
	CODIGO_GESTOR_REDE("CODIGO-MCI-GESTOR-REDE", 9),
	NOME_GESTOR_REDE("NOME-GESTOR-REDE", 30),
	;

	// Uso de transient, conforme https://fontes.intranet.bb.com.br/dev/publico/atendimento/-/issues/3050
	transient IChaveMonitorada chaveMonitorada;

	ChavesMonitoradasGestorRede(String chave, int tamanho) {
		chaveMonitorada = new ChaveMonitorada(chave, tamanho);
	}

	@Override
	public IChaveMonitorada get() {
		return chaveMonitorada;
	}
}
