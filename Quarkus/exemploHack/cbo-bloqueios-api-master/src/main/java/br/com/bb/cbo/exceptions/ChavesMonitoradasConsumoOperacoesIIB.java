package br.com.bb.cbo.exceptions;

import br.com.bb.dev.ext.error.ChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;

public enum ChavesMonitoradasConsumoOperacoesIIB implements IEnumChaveMonitorada {
	CODIGO_RETORNO_CONSUMO_OPERACAO_IIB("COD-RETORNO-CONSUMO-OPER-IIB", 9),
	CODIGO_ERRO_CONSUMO_OPERACAO_IIB("COD-ERRO-CONSUMO-OPER-IIB", 256),
	MENSAGEM_CONSUMO_OPERACAO_IIB("MENSAGEM-CONSUMO-OPER-IIB", 256),
	SEQUENCIAL_CONSUMO_OPERACAO_IIB("SEQUENCIAL-CONSUMO-OPER-IIB", 256),
	;

	IChaveMonitorada chaveMonitorada;

	ChavesMonitoradasConsumoOperacoesIIB(String chave, int tamanho) {
		chaveMonitorada = new ChaveMonitorada(chave, tamanho);
	}

	@Override
	public IChaveMonitorada get() {
		return chaveMonitorada;
	}
}
