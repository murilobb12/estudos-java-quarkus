//package br.com.bb.cbo.gestorRedeCorrespondenteBancario;
//
//import java.time.LocalDate;
//
//import javax.inject.Inject;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import br.com.bb.dev.ext.exceptions.ErroNegocialException;
//import io.quarkus.test.junit.QuarkusTest;
//
//@QuarkusTest
//public class GestorRedeCorrespondenteBancarioServiceTest {
//    
//	@Inject
//	GestorRedeCorrespondenteBancarioService service;
//	
//    @Test
//    @DisplayName("TESTE GESTOR DE REDE - consultarUltimoGestorRedePorCodigoInstituicaoECodigoCorrespondenteEData()")
//    public void consultarUltimoGestorRedePorCodigoInstituicaoECodigoCorrespondenteEData() throws ErroNegocialException {
//    	
//    	Assertions.assertEquals(123789456,
//    			service.consultarUltimoGestorRedePorCodigoInstituicaoECodigoCorrespondenteEData(
//    					1, 123789456, LocalDate.now()).getCodigoGestorRedeCorrespondente());
//    	
//    	Assertions.assertNull(service.consultarUltimoGestorRedePorCodigoInstituicaoECodigoCorrespondenteEData(
//				1, 987654321, LocalDate.now()));
//    }
//}
