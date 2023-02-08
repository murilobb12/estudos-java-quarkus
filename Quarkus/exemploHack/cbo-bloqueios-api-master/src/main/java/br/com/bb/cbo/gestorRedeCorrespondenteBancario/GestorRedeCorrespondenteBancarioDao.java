package br.com.bb.cbo.gestorRedeCorrespondenteBancario;

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
public class GestorRedeCorrespondenteBancarioDao {

    @PersistenceContext
    EntityManager em;
    
	@Inject
	Logger logger;

	ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio
	consultarGestorRedeCorrespondenteBancarioOperacoesCRUDBloqueioNegocialOperador(
			int codigoInstituicao, int codigoGestorRedeCorrespondente) throws ErroSqlException {
    	String nameQuery = "CONSULTAR_GESTOR_REDE_PARA_OPERACAO_CONSULTAR_BLOQUEIO";
    	
    	TypedQuery<ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio> query = 
    			em.createNamedQuery(nameQuery, ModelGestorRedeCorrespondenteBancarioParaOperacaoConsultarBloqueio.class);
    	
    	query.setParameter("codigoInstituicao", codigoInstituicao);
    	query.setParameter("codigoGestorRedeCorrespondente", codigoGestorRedeCorrespondente);
    	
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
