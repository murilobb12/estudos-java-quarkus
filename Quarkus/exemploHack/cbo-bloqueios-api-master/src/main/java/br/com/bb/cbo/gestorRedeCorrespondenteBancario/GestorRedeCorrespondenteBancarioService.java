package br.com.bb.cbo.gestorRedeCorrespondenteBancario;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.bb.cbo.exceptions.ChavesMonitoradasGestorRede;
import br.com.bb.cbo.exceptions.ChavesMonitoradasInstituicao;
import br.com.bb.cbo.exceptions.ErroSqlException;
import br.com.bb.cbo.exceptions.ErrosSistema;
import br.com.bb.dev.ext.error.ChavesMonitoradasPadrao;
import br.com.bb.dev.ext.exceptions.ErroNegocialException;
import io.opentracing.Tracer;

@Traced
@RequestScoped
public class GestorRedeCorrespondenteBancarioService {
	
    @Inject
    Tracer configuredTracer;
    
    @Inject
    GestorRedeCorrespondenteBancarioDao gestorRedeCorrespondenteBancarioDao; 

    public ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio
    consultarGestorRedeCorrespondenteBancarioOperacoesCRUDBloqueioNegocialOperador(
    		int codigoInstituicao, int codigoGestorRedeCorrespondente) throws ErroNegocialException {

    	try {
    		ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio gestorRede = 
    				gestorRedeCorrespondenteBancarioDao.consultarGestorRedeCorrespondenteBancarioOperacoesCRUDBloqueioNegocialOperador(
    						codigoInstituicao, codigoGestorRedeCorrespondente);
    		
    		return gestorRede;    		
    	} catch (ErroSqlException e) {
    		throw new ErroNegocialException(ErrosSistema.ERRO_GENERICO_SQL.get()
    				.addVariavel(ChavesMonitoradasInstituicao.CODIGO_INSTITUICAO.get(),
    						String.valueOf(codigoInstituicao))
    				.addVariavel(ChavesMonitoradasGestorRede.CODIGO_GESTOR_REDE.get(),
    						String.valueOf(codigoGestorRedeCorrespondente))
    				.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(),
    						"Houve algum erro de Banco de Dados ao realizar uma consulta à tabela DB2CBO.GST_REDE_CRS_BCRO.")
    				);
    	}
    }  
}
