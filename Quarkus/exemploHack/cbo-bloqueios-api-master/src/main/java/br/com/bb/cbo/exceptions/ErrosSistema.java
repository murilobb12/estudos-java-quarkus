package br.com.bb.cbo.exceptions;

import br.com.bb.dev.ext.error.ChavesMonitoradasSQL;
import br.com.bb.dev.ext.error.Erro;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumErro;
import br.com.bb.dev.ext.error.interfaces.IErro;

public enum ErrosSistema implements IEnumErro {
	
	ERRO_SQL("900", "Erro no sistema", ChavesMonitoradasSQL.class),
    
    ERRO_GENERICO_SQL("901", // Mensageria: 6515411
    		"Ocorreu um erro durante a execução da sua solicitação no banco de dados.",
    		ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasHistoricoCorrespondenteBancario.class,
    		ChavesMonitoradasCorrespondenteBancario.class,
    		ChavesMonitoradasUsuarioLogado.class,
    		ChavesMonitoradasGestorRede.class),
    
    ERRO_GENERICO_RECUPERACAO_DADOS_BASE_DADOS("100", // Mensageria: 6516704
    		"Erro na recuperação dos dados. Tente novamente mais tarde e se o erro persistir acione o Suporte Técnico.",
    		ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasCorrespondenteBancario.class,
    		ChavesMonitoradasHistoricoCorrespondenteBancario.class,
    		ChavesMonitoradasGestorRede.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class),
    
    ERRO_GENERICO_BUSCA_SEM_RESULTADO("101", // Mensageria: 6515466
    		"Busca de dados não encontrou resultados.",
    		ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasCorrespondenteBancario.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class),
    
	ERRO_GENERICO_CONSUMO_OPERACAO_IIB("102", // Mensageria: 6516552
			"Erro no sistema. Tente novamente mais tarde e se o erro persistir acione o Suporte Técnico.",
			ChavesMonitoradasCliente.class,
			ChavesMonitoradasConsumoOperacoesIIB.class,
			ChavesMonitoradasOperadorCorrespondente.class),
	
	ERRO_GENERICO_REQUISICAO_SEM_VALORES("103", // Mensageria: 6516834
			"Campos obrigatórios não preenchidos.",
			ChavesMonitoradasInstituicao.class,
			ChavesMonitoradasCorrespondenteBancario.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class),
	
	ERRO_GENERICO_REQUISICAO_DADOS_INVALIDOS("104", // Mensageria: 6516935
			"Dados inválidos. Campos prenchidos incorretamente.",
			ChavesMonitoradasInstituicao.class,
			ChavesMonitoradasCorrespondenteBancario.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class),
	
	ERRO_GENERICO_ALTERAR_REGISTRO_SEM_DADOS_ALTERADOS("105", // Mensageria: 6516973
			"A solicitação efetuada não apresenta mudança nos dados atuais.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class),
	
	ERRO_CODIGO_IDENTIFICACAO_CORRESPONDENTE_SEM_BLOQUEIO("106", // Mensageria: 6518746
			"A Chave J informada não possui bloqueio ativo nesta data.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class), 
	
	ERRO_CODIGO_IDENTIFICACAO_CORRESPONDENTE_NAO_LOCALIZADO("107",  // Mensageria: 6518839
			"A Chave J informada não foi localizada.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class),
	
	ERRO_CODIGO_IDENTIFICACAO_CORRESPONDENTE_POSSUI_BLOQUEIO_ATIVO("108", // Mensageria: 6518971
			"Não é permitido incluir novo bloqueio para uma Chave J com bloqueio vigente.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class),
	
	ERRO_TEXTO_JUSTIFICATIVA_INVALIDO("109", // Mensageria: 6519157
			"Texto de justificativa do bloqueio é obrigatório para o motivo Outros.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class),
	
	ERRO_TEXTO_JUSTIFICATIVA_EXPIROU_TAMANHO_MAXIMO("110", // Mensageria: 6519215
			"Texto de justificativa do bloqueio não pode ter mais de 500 caracteres.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class),
	
	ERRO_CODIGO_TIPO_BLOQUEIO_INVALIDO("111", // Mensageria: 6519254
			"Código do tipo de bloqueio inválido.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class),
	
	ERRO_DATA_FIM_BLOQUEIO_INVALIDA("112", // Mensageria: 6519408
			"Data do fim de vigência do bloqueio inválida.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class),
	
	ERRO_DATA_FIM_BLOQUEIO_MENOR_QUE_DATA_ATUAL("113", // Mensageria: 6519596
			"Data do fim do bloqueio não pode ser anterior à data atual.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class),
	
	ERRO_CLIENTE_SEM_BLOQUEIOS("114", // Mensageria: 6519707
			"O cliente informado não possui Chave J com bloqueio ativo nesta data.",
			ChavesMonitoradasInstituicao.class,
			ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class),
	
	ERRO_EXCLUIR_BLOQUEIO_NAO_ATIVO("115", // Mensageria: 6519723
			"A Chave J não possui bloqueio ativo para ser desbloqueada.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class),
	
	ERRO_ALTERAR_BLOQUEIO_NAO_ATIVO("116", // Mensageria: 6519838
			"A Chave J não possui bloqueio ativo para ser alterado.",
			ChavesMonitoradasInstituicao.class,
    		ChavesMonitoradasOperadorCorrespondente.class,
    		ChavesMonitoradasDadosBloqueioOperadorCorrespondente.class,
    		ChavesMonitoradasUsuarioLogado.class);
	
    String codigo;
    String mensagem;
    Class<? extends IEnumChaveMonitorada>[] enumsChaves;

    @SafeVarargs
	ErrosSistema(String codigo, String mensagem, Class<? extends IEnumChaveMonitorada>... enumsChaves) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.enumsChaves = enumsChaves;
    }
    
    ErrosSistema(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    @Override
    public IErro get() {
        return new Erro(codigo, mensagem, enumsChaves);
    }
}
