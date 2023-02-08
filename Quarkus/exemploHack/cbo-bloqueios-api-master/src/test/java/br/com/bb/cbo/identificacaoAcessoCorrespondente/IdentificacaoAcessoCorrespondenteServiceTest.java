package br.com.bb.cbo.identificacaoAcessoCorrespondente;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.bb.dev.ext.exceptions.ErroNegocialException;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class IdentificacaoAcessoCorrespondenteServiceTest {
    
	@Inject
	IdentificacaoAcessoCorrespondenteService service;
	
    @Test
    @DisplayName("TESTE CONSULTAR BLOQUEIO NEGOCIAL - consultarBloqueioNegocialOperador()")
    public void consultarBloqueioNegocialOperador() throws ErroNegocialException {
    	
    	Assertions.assertEquals(1,
    			service.consultarBloqueioNegocialOperador(
    					1, "J1234567").getCodigoInstituicao());
    }
    
    @Test
    @DisplayName("TESTE INCLUIR BLOQUEIO NEGOCIAL - incluirBloqueioNegocialOperador()")
    public void incluirBloqueioNegocialOperador() throws ErroNegocialException {
    	
    	Assertions.assertEquals(true,
    			service.incluirBloqueioNegocialOperador(
    					1, "J1593578", 1, "F7003009", null, "Texto do bloqueio"));
    }
    
    @Test
    @DisplayName("TESTE ALTERAR BLOQUEIO NEGOCIAL - alterarBloqueioNegocialOperador()")
    public void alterarBloqueioNegocialOperador() throws ErroNegocialException {
    	
    	Assertions.assertEquals(true,
    			service.alterarBloqueioNegocialOperador(
    					1, "J7894561", null, "F7003009"));
    }
    
    @Test
    @DisplayName("TESTE EXCLUIR BLOQUEIO NEGOCIAL - excluirBloqueioNegocialOperador()")
    public void excluirBloqueioNegocialOperador() throws ErroNegocialException {
    	
    	Assertions.assertEquals(true,
    			service.excluirBloqueioNegocialOperador(
    					1, "J4561239", "F7003009"));
    }
}
