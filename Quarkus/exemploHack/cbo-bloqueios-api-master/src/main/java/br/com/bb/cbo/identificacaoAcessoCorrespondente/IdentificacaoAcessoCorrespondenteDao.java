package br.com.bb.cbo.identificacaoAcessoCorrespondente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class IdentificacaoAcessoCorrespondenteDao {
	
	// A constante a seguir é utilizada na exposição da operação (Classe br.com.bb.cbo.operacoes.Op6483891v1.java) 
	public static final int TAM_MAX_REGISTROS_POR_PAGINA_OPERACAO_LISTAR = 100;

    @PersistenceContext
    EntityManager em;
    
	@Inject
	Logger logger;
	
	ModelIdentificacaoAcessoCorrespondente consultarIdentificacaoAcessoCorrespondente(
			Integer codigoInstituicao, String codigoIdentificacaoCorrespondente) throws ErroSqlException {
    	String nameQuery = "CONSULTAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE";
    	
    	TypedQuery<ModelIdentificacaoAcessoCorrespondente> query = 
    			em.createNamedQuery(nameQuery, ModelIdentificacaoAcessoCorrespondente.class);

    	query.setParameter("codigoInstituicao", codigoInstituicao);
    	
    	if (codigoIdentificacaoCorrespondente != null) {
        	query.setParameter("codigoIdentificacaoCorrespondente", codigoIdentificacaoCorrespondente.trim());
        } else {
        	query.setParameter("codigoIdentificacaoCorrespondente", codigoIdentificacaoCorrespondente);
        }
    	
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
	
	List<ModelIdentificacaoAcessoCorrespondente> listarIdentificacaoAcessoCorrespondente(
			Integer codigoInstituicao, Integer codigoOperadorCorrespondente, Integer numeroPaginaPesquisa) throws ErroSqlException {
		int registroInicial = 0;
		int registroFinal = 0;
    	String nameQuery = "LISTAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE";
    	
    	registroInicial = ((numeroPaginaPesquisa - 1) * 100) + 1;
    	registroFinal = (numeroPaginaPesquisa * 100) + 1;
    	
    	TypedQuery<ModelIdentificacaoAcessoCorrespondente> query = 
    			em.createNamedQuery(nameQuery, ModelIdentificacaoAcessoCorrespondente.class);

    	query.setParameter("codigoInstituicao", codigoInstituicao);
    	query.setParameter("codigoOperadorCorrespondente", codigoOperadorCorrespondente);
    	query.setParameter("registroInicial", registroInicial);
    	query.setParameter("registroFinal", registroFinal);
    	
        try {
        	em.clear();
        	return query.getResultList();
        } catch (PersistenceException e){
        	logger.error(Arrays.toString(e.getStackTrace()));
            throw new ErroSqlException(e);
        }
    }
	
    @Transactional
    int incluirBloqueioNegocialOperador(Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		Integer codigoTipoBloqueioNegocial, String codigoChaveUsuario,
    		LocalDate dataAlteracao, LocalTime horaAlteracao,
    		LocalDate dataInicioBloqueioNegocial, LocalDate dataFimBloqueioNegocial,
    		Integer codigoEstadoAtividadeBloqueio, String textoJustificativaBloqueioNegocial) throws ErroSqlException {
        String nameQuery = "ALTERAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE_PARA_OPERACAO_INCLUIR_BLOQUEIO";
        
        Query query = em.createNamedQuery(nameQuery);
        
        query.setParameter("codigoInstituicao", codigoInstituicao);
        
        if (codigoIdentificacaoCorrespondente != null) {
        	query.setParameter("codigoIdentificacaoCorrespondente", codigoIdentificacaoCorrespondente.trim());
        } else {
        	query.setParameter("codigoIdentificacaoCorrespondente", codigoIdentificacaoCorrespondente);
        }
        
    	query.setParameter("codigoTipoBloqueioNegocial", codigoTipoBloqueioNegocial);
    	
    	if (codigoChaveUsuario != null) {
        	query.setParameter("codigoChaveUsuario", codigoChaveUsuario.trim());
        } else {
        	query.setParameter("codigoChaveUsuario", codigoChaveUsuario);
        }
    	
    	query.setParameter("dataAlteracao", dataAlteracao);
    	query.setParameter("horaAlteracao", horaAlteracao);
    	query.setParameter("dataInicioBloqueioNegocial", dataInicioBloqueioNegocial);
    	query.setParameter("dataFimBloqueioNegocial", dataFimBloqueioNegocial);
    	query.setParameter("codigoEstadoAtividadeBloqueio", codigoEstadoAtividadeBloqueio);
    	
    	if (textoJustificativaBloqueioNegocial != null) {
        	query.setParameter("textoJustificativaBloqueioNegocial", textoJustificativaBloqueioNegocial.trim());
        } else {
        	query.setParameter("textoJustificativaBloqueioNegocial", textoJustificativaBloqueioNegocial);
        }
    	
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
    
    @Transactional
    int alterarBloqueioNegocialOperador(Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		LocalDate dataFimBloqueioNegocial, String codigoChaveUsuario,
    		LocalDate dataAlteracao, LocalTime horaAlteracao) throws ErroSqlException {
        String nameQuery = "ALTERAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE_PARA_OPERACAO_ALTERAR_BLOQUEIO";
        
        Query query = em.createNamedQuery(nameQuery);
        
        if (codigoChaveUsuario != null) {
        	query.setParameter("codigoChaveUsuario", codigoChaveUsuario.trim());
        } else {
        	query.setParameter("codigoChaveUsuario", codigoChaveUsuario);
        }
        
        query.setParameter("dataAlteracao", dataAlteracao);
    	query.setParameter("horaAlteracao", horaAlteracao);
        query.setParameter("codigoInstituicao", codigoInstituicao);
    	
    	if (codigoIdentificacaoCorrespondente != null) {
        	query.setParameter("codigoIdentificacaoCorrespondente", codigoIdentificacaoCorrespondente.trim());
        } else {
        	query.setParameter("codigoIdentificacaoCorrespondente", codigoIdentificacaoCorrespondente);
        }
    	
    	query.setParameter("dataFimBloqueioNegocial", dataFimBloqueioNegocial);
    	
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
    
    @Transactional
    int excluirBloqueioNegocialOperador(Integer codigoInstituicao, String codigoIdentificacaoCorrespondente,
    		String codigoChaveUsuario, LocalDate dataAlteracao,
    		LocalTime horaAlteracao, Integer codigoEstadoAtividadeBloqueio) throws ErroSqlException {
        String nameQuery = "ALTERAR_IDENTIFICACAO_ACESSO_CORRESPONDENTE_PARA_OPERACAO_EXCLUIR_BLOQUEIO";
        
        Query query = em.createNamedQuery(nameQuery);
        
        query.setParameter("codigoEstadoAtividadeBloqueio", codigoEstadoAtividadeBloqueio);
        
        if (codigoChaveUsuario != null) {
        	query.setParameter("codigoChaveUsuario", codigoChaveUsuario.trim());
        } else {
        	query.setParameter("codigoChaveUsuario", codigoChaveUsuario);
        }
        
        query.setParameter("dataAlteracao", dataAlteracao);
    	query.setParameter("horaAlteracao", horaAlteracao);
        query.setParameter("codigoInstituicao", codigoInstituicao);
        
        if (codigoIdentificacaoCorrespondente != null) {
        	query.setParameter("codigoIdentificacaoCorrespondente", codigoIdentificacaoCorrespondente.trim());
        } else {
        	query.setParameter("codigoIdentificacaoCorrespondente", codigoIdentificacaoCorrespondente);
        }
    	
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
