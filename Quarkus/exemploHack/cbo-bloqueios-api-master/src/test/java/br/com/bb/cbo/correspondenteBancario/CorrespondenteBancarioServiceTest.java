package br.com.bb.cbo.correspondenteBancario;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.bb.dev.ext.exceptions.ErroNegocialException;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CorrespondenteBancarioServiceTest {
    
	@Inject
	CorrespondenteBancarioService service;
	
    @Test
    @DisplayName("TESTE CORRESPONDENTE BANCÁRIO - consultarCorrespondenteBancarioParaOperacoesCRUDBloqueioNegocialOperador()")
    public void consultarCorrespondenteBancarioParaOperacoesCRUDBloqueioNegocialOperador() throws ErroNegocialException {
    	
    	Assertions.assertEquals(123456789,
    			service.consultarCorrespondenteBancarioParaOperacoesCRUDBloqueioNegocialOperador(
    					1, 123456789).getCodigoCorrespondente());
    	
    	Assertions.assertNull(service.consultarCorrespondenteBancarioParaOperacoesCRUDBloqueioNegocialOperador(1, 987654321));
    }
}
