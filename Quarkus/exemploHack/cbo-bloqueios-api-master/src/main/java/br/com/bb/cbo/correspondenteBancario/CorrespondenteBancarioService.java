package br.com.bb.cbo.correspondenteBancario;

import java.time.LocalDate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.bb.cbo.exceptions.ChavesMonitoradasCorrespondenteBancario;
import br.com.bb.cbo.exceptions.ChavesMonitoradasHistoricoCorrespondenteBancario;
import br.com.bb.cbo.exceptions.ChavesMonitoradasInstituicao;
import br.com.bb.cbo.exceptions.ErroSqlException;
import br.com.bb.cbo.exceptions.ErrosSistema;
import br.com.bb.dev.ext.error.ChavesMonitoradasPadrao;
import br.com.bb.dev.ext.exceptions.ErroNegocialException;
import io.opentracing.Tracer;

@Traced
@RequestScoped
public class CorrespondenteBancarioService {
	
    @Inject
    Tracer configuredTracer;
    
    @Inject
    CorrespondenteBancarioDao correspondenteBancarioDao;
    
    @Inject
    HistoricoCorrespondenteBancarioDao historicoCorrespondenteBancarioDao;

    public ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio
    consultarCorrespondenteBancarioParaOperacoesCRUDBloqueioNegocialOperador(
    		int codigoInstituicao, int codigoCorrespondente) throws ErroNegocialException {

    	try {
    		ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio correspondenteBancario = 
    				correspondenteBancarioDao.consultarCorrespondenteBancarioParaOperacoesCRUDBloqueioNegocialOperador(
    						codigoInstituicao, codigoCorrespondente);
    		
    		return correspondenteBancario;
    	} catch (ErroSqlException e) {
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(), String.valueOf(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasCorrespondenteBancario.CODIGO_CORRESPONDENTE.get(),
    						String.valueOf(codigoCorrespondente))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Houve algum erro de Banco de Dados ao realizar uma consulta à tabela DB2CBO.CRS_BCRO.")
    				);
    	}
    }
    
    public ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio
    consultarHistoricoCorrespondenteBancarioParaOperacoesCRUDBloqueioNegocialOperador(
    		int codigoInstituicao, int codigoCorrespondente, LocalDate dataBloqueio) throws ErroNegocialException {

    	try {
    		ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio historicoCorrespondenteBancario = 
    				historicoCorrespondenteBancarioDao.consultarHistoricoCorrespondenteBancarioParaCRUDBloqueioNegocialOperador(
    						codigoInstituicao, codigoCorrespondente, dataBloqueio);
    		
    		return historicoCorrespondenteBancario;
    	} catch (ErroSqlException e) {
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						String.valueOf(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasCorrespondenteBancario.CODIGO_CORRESPONDENTE.get(),
    						String.valueOf(codigoCorrespondente))
    				.addVariavel(ChavesMonitoradasHistoricoCorrespondenteBancario.DATA_PESQUISADA.get(),
    						converteObjectParaString(dataBloqueio))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Houve algum erro de Banco de Dados ao realizar uma consulta à tabela DB2CBO.HST_CRS_BCRO.")
    				);
    	}
    }
    
	// ******************************************************************************************************
	// OS MÉTODOS DA SEÇÃO A SEGUIR SÃO AUXILIARES E SÃO USADOS SOMENTE NESTA CLASSE (PRIVADOS).
	// 
	// ******************************************************************************************************
    
    private String converteObjectParaString(Object o) {
    	if (o != null) {
    		return o.toString();
    	} else {
    		return "";
    	}
    }
}
