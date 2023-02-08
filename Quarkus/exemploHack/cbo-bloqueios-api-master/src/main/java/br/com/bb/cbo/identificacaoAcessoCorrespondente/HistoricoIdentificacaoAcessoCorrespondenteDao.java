package br.com.bb.cbo.identificacaoAcessoCorrespondente;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;

import br.com.bb.cbo.exceptions.ErroSqlException;

@Traced
@RequestScoped
public class HistoricoIdentificacaoAcessoCorrespondenteDao {

    @PersistenceContext
    EntityManager em;
    
	@Inject
	Logger logger;
	
	List<ModelHistoricoIdentificacaoAcessoCorrespondente> listarHistoricoIdentificacaoAcessoCorrespondentePorDataInicioBloqueio(
			Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
			LocalDate dataInicioBloqueioNegocial) throws ErroSqlException {
    	String nameQuery = "LISTAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE_POR_DATA_INICIO_BLOQUEIO_NEGOCIAL_ORDER_BY_TIMESTAMP_DESC";
    	
    	TypedQuery<ModelHistoricoIdentificacaoAcessoCorrespondente> query = 
    			em.createNamedQuery(nameQuery, ModelHistoricoIdentificacaoAcessoCorrespondente.class);

    	query.setParameter("codigoInstituicao", codigoInstituicao);
    	query.setParameter("codigoIdentificacaoCorrespondente", codigoIdentificacaoCorrespondente);
    	query.setParameter("dataInicioBloqueioNegocial", dataInicioBloqueioNegocial);
    	
        try {
        	em.clear();
        	return query.getResultList();
        } catch (PersistenceException e){
        	logger.error(Arrays.toString(e.getStackTrace()));
            throw new ErroSqlException(e);
        }
    }
    
    @Transactional
    int incluirHistoricoIdentificacaoAcessoCorrespondente(
    		ModelHistoricoIdentificacaoAcessoCorrespondente historicoIdentificacaoAcessoCorrespondente) throws ErroSqlException {
        String nameQuery = "INCLUIR_HISTORICO_IDENTIFICACAO_ACESSO_CORRESPONDENTE";
        
        Query query = em.createNamedQuery(nameQuery);
        
        query.setParameter("codigoInstituicao", historicoIdentificacaoAcessoCorrespondente.getCodigoInstituicao());
    	query.setParameter("codigoIdentificacaoCorrespondente",
    			historicoIdentificacaoAcessoCorrespondente.getCodigoIdentificacaoCorrespondente());
    	query.setParameter("timestampAtualizacaoHistorico", historicoIdentificacaoAcessoCorrespondente.getTimestampAtualizacaoHistorico());
    	query.setParameter("codigoEstadoAtividade", historicoIdentificacaoAcessoCorrespondente.getCodigoEstadoAtividade());
    	query.setParameter("codigoCorrespondente", historicoIdentificacaoAcessoCorrespondente.getCodigoCorrespondente());
    	query.setParameter("numeroUltimoSequencialIdentificacao",
    			historicoIdentificacaoAcessoCorrespondente.getNumeroUltimoSequencialIdentificacao());
    	query.setParameter("codigoChaveUsuario", historicoIdentificacaoAcessoCorrespondente.getCodigoChaveUsuario());
    	query.setParameter("dataAlteracao", historicoIdentificacaoAcessoCorrespondente.getDataAlteracao());
    	query.setParameter("horaAlteracao", historicoIdentificacaoAcessoCorrespondente.getHoraAlteracao());
    	query.setParameter("codigoOperadorCorrespondente", historicoIdentificacaoAcessoCorrespondente.getCodigoOperadorCorrespondente());
    	query.setParameter("codigoAcessoProvisorioOperador",
    			historicoIdentificacaoAcessoCorrespondente.getCodigoAcessoProvisorioOperador());
    	query.setParameter("codigoLojaCorrespondente", historicoIdentificacaoAcessoCorrespondente.getCodigoLojaCorrespondente());
    	query.setParameter("dataInicioBloqueioNegocial", historicoIdentificacaoAcessoCorrespondente.getDataInicioBloqueioNegocial());
    	query.setParameter("dataFimBloqueioNegocial", historicoIdentificacaoAcessoCorrespondente.getDataFimBloqueioNegocial());
    	query.setParameter("codigoEstadoAtividadeBloqueio", historicoIdentificacaoAcessoCorrespondente.getCodigoEstadoAtividadeBloqueio());
    	query.setParameter("codigoTipoBloqueioNegocial", historicoIdentificacaoAcessoCorrespondente.getCodigoTipoBloqueioNegocial());
    	query.setParameter("textoJustificativaBloqueioNegocial",
    			historicoIdentificacaoAcessoCorrespondente.getTextoJustificativaBloqueioNegocial());
    	
    	int resultado = 0;
        
        try {
        	resultado = query.executeUpdate();
            em.flush();
            return resultado;        
        } catch (PersistenceException e) {
        	logger.error(Arrays.toString(e.getStackTrace()));
            throw new ErroSqlException(e);
        }
    }
}
