package br.com.bb.cbo.exceptions;

import br.com.bb.dev.ext.error.ChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;

public enum ChavesMonitoradasCliente implements IEnumChaveMonitorada {
	MCI_CLIENTE("MCI-CLIENTE", 9),
	CPF_CLIENTE("CPF-CLIENTE", 18),
	NOME_CLIENTE("NOME-CLIENTE", 60),
	;

	IChaveMonitorada chaveMonitorada;

	ChavesMonitoradasCliente(String chave, int tamanho) {
		chaveMonitorada = new ChaveMonitorada(chave, tamanho);
	}

	@Override
	public IChaveMonitorada get() {
		return chaveMonitorada;
	}
}
