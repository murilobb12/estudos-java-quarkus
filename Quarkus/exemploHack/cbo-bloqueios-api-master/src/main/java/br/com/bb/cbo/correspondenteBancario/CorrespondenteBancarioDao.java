package br.com.bb.cbo.correspondenteBancario;

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
public class CorrespondenteBancarioDao {

    @PersistenceContext
    EntityManager em;
    
	@Inject
	Logger logger;
	
	ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio consultarCorrespondenteBancarioParaOperacoesCRUDBloqueioNegocialOperador(
			int codigoInstituicao, int codigoCorrespondente) throws ErroSqlException {
    	String nameQuery = "CONSULTAR_CORRESPONDENTE_POR_CODIGO_INSTITUICAO_E_CODIGO_CORRESPONDENTE_PARA_OPERACAO_CONSULTAR_BLOQUEIO";
    	
    	TypedQuery<ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio> query = 
    			em.createNamedQuery(nameQuery, ModelCorrespondenteBancarioParaOperacaoConsultarBloqueio.class);

    	query.setParameter("codigoInstituicao", codigoInstituicao);
    	query.setParameter("codigoCorrespondente", codigoCorrespondente);
    	
        try {
        	em.clear();
            return query.getSingleResult();
        } catch (NoResultException e){
        	logger.error(Arrays.toString(e.getStackTrace()));
        	return null;
        } catch (PersistenceException e){
        	logger.error(Arrays.toString(e.getStackTrace()));
            throw new ErroSqlException(e);
        }
    }

}
