package br.com.bb.cbo.exceptions;

import br.com.bb.dev.ext.error.ChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;

public enum ChavesMonitoradasDadosBloqueioOperadorCorrespondente implements IEnumChaveMonitorada {
	NUMERO_PAGINA_PESQUISA("NR-PAGINA-SOLICITADA", 9),
	CODIGO_TIPO_BLOQUEIO_NEGOCIAL("TIPO-BLOQUEIO", 70),
	CODIGO_ESTADO_ATIVIDADE_BLOQUEIO("CODIGO-ESTADO-BLOQUEIO", 20),
	TIMESTAMP_BLOQUEIO_NEGOCIAL("TIMESTAMP-BLOQUEIO", 30),
	DATA_INICIO_BLOQUEIO_NEGOCIAL("DATA-INICIO-BLOQUEIO", 10),
	DATA_FIM_BLOQUEIO_NEGOCIAL("DATA-FIM-BLOQUEIO", 10),
	TEXTO_JUSTIFICATIVA_BLOQUEIO_NEGOCIAL("TEXTO-JUSTIFICATIVA-BLOQUEIO", 500),
	;

	IChaveMonitorada chaveMonitorada;

	ChavesMonitoradasDadosBloqueioOperadorCorrespondente(String chave, int tamanho) {
		chaveMonitorada = new ChaveMonitorada(chave, tamanho);
	}

	@Override
	public IChaveMonitorada get() {
		return chaveMonitorada;
	}
}
