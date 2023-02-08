package br.com.bb.cbo.correspondenteBancario;

import java.time.LocalDate;
import java.util.Arrays;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;

import br.com.bb.cbo.exceptions.ErroSqlException;

@Traced
@RequestScoped
public class HistoricoCorrespondenteBancarioDao {

    @PersistenceContext
    EntityManager em;
    
	@Inject
	Logger logger;
	
	ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio
	consultarHistoricoCorrespondenteBancarioParaCRUDBloqueioNegocialOperador(
			int codigoInstituicao, int codigoCorrespondente, LocalDate dataBloqueio) throws ErroSqlException {
    	String nameQuery = "CONSULTAR_HISTORICO_CORRESPONDENTE_POR_CODIGO_INSTITUICAO_E_CODIGO_CORRESPONDENTE_E_DATA";
    	
    	TypedQuery<ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio> query = 
    			em.createNamedQuery(nameQuery, ModelHistoricoCorrespondenteBancarioParaOperacaoConsultarBloqueio.class);

    	query.setParameter("codigoInstituicao", codigoInstituicao);
    	query.setParameter("codigoCorrespondente", codigoCorrespondente);
    	query.setParameter("dataBloqueio", dataBloqueio);
    	
        try {
        	em.clear();
            return query.getSingleResult();
        } catch (NoResultException e){
        	return null;
        } catch (PersistenceException e){
        	logger.error(Arrays.toString(e.getStackTrace()));
            throw new ErroSqlException(e);
        }
    }

}
