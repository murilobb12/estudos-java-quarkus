package br.com.bb.cbo.operacoes;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.bb.cbo.operacao.incluirBloqueioNegocialOperadorCorrespondenteBancarioV1.bean.requisicao.DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class Op6463945v1Test {
    @Test
    public void incluirBloqueioNegocialOperador() {
    	
    	DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario requisicao =
    			new DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J1593111");
    	requisicao.setCodigoTipoBloqueioNegocial(3);
    	requisicao.setCodigoChaveUsuario("F7003009");
    	requisicao.setDataFimBloqueioNegocial(LocalDate.now().toString());
    	requisicao.setQuantidadeTextoJustificativaBloqueio(5);
    	requisicao.setTextoJustificativaBloqueioNegocial("Texto");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463945v1")
            .then()
            .statusCode(200);
        
        // Teste de inclusão com dados inválidos.
        requisicao = new DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(0);
    	requisicao.setCodigoIdentificacaoCorrespondente(null);
    	requisicao.setCodigoTipoBloqueioNegocial(3);
    	requisicao.setCodigoChaveUsuario(null);
    	requisicao.setDataFimBloqueioNegocial(null);
    	requisicao.setQuantidadeTextoJustificativaBloqueio(5);
    	requisicao.setTextoJustificativaBloqueioNegocial("Texto");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463945v1")
            .then()
            .statusCode(422);
        
        // Teste de inclusão com dados inválidos.
        requisicao = new DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
        requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J1593111");
    	requisicao.setCodigoTipoBloqueioNegocial(3);
    	requisicao.setCodigoChaveUsuario("F7003009123");
    	requisicao.setDataFimBloqueioNegocial(LocalDate.now().toString());
    	requisicao.setQuantidadeTextoJustificativaBloqueio(5);
    	requisicao.setTextoJustificativaBloqueioNegocial("Texto");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463945v1")
            .then()
            .statusCode(422);
        
        // Teste de inclusão com dados inválidos.
        requisicao = new DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
        requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J1593111");
    	requisicao.setCodigoTipoBloqueioNegocial(3);
    	requisicao.setCodigoChaveUsuario("F7003009");
    	requisicao.setDataFimBloqueioNegocial(LocalDate.now().toString());
    	requisicao.setQuantidadeTextoJustificativaBloqueio(5);
    	requisicao.setTextoJustificativaBloqueioNegocial(null);
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463945v1")
            .then()
            .statusCode(422);
        
        // Teste de inclusão com dados inválidos.
        requisicao = new DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
        requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J1593111");
    	requisicao.setCodigoTipoBloqueioNegocial(3);
    	requisicao.setCodigoChaveUsuario("F7003009");
    	requisicao.setDataFimBloqueioNegocial(LocalDate.now().toString());
    	requisicao.setQuantidadeTextoJustificativaBloqueio(5);
    	requisicao.setTextoJustificativaBloqueioNegocial("sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf" +
    			"sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf" +
    			"sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf" +
    			"sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf" +
    			"sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf" +
    			"sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf" +
    			"sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf" +
    			"sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf" +
    			"sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf" +
    			"sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf" +
    			"sldfsldkfjlskjdf klsjdfklj slkdfjlk sjdfkl jslkdfj lskjdfkj sdlkfj slkdjf");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463945v1")
            .then()
            .statusCode(422);
        
        // Teste de inclusão com dados inválidos.
        requisicao = new DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
        requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J1593111");
    	requisicao.setCodigoTipoBloqueioNegocial(4);
    	requisicao.setCodigoChaveUsuario("F7003009");
    	requisicao.setDataFimBloqueioNegocial(LocalDate.now().toString());
    	requisicao.setQuantidadeTextoJustificativaBloqueio(5);
    	requisicao.setTextoJustificativaBloqueioNegocial("Texto");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463945v1")
            .then()
            .statusCode(422);
        
        // Teste de inclusão com dados inválidos.
        requisicao = new DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
        requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J1593111");
    	requisicao.setCodigoTipoBloqueioNegocial(3);
    	requisicao.setCodigoChaveUsuario("F7003009");
    	requisicao.setDataFimBloqueioNegocial(LocalDate.now().minusDays(1).toString());
    	requisicao.setQuantidadeTextoJustificativaBloqueio(5);
    	requisicao.setTextoJustificativaBloqueioNegocial("Texto");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463945v1")
            .then()
            .statusCode(422);
        
        // Teste de inclusão com dados inválidos.
        requisicao = new DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
        requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J1593");
    	requisicao.setCodigoTipoBloqueioNegocial(3);
    	requisicao.setCodigoChaveUsuario("F7003009");
    	requisicao.setDataFimBloqueioNegocial(LocalDate.now().toString());
    	requisicao.setQuantidadeTextoJustificativaBloqueio(5);
    	requisicao.setTextoJustificativaBloqueioNegocial("Texto");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463945v1")
            .then()
            .statusCode(422);
        
        // Teste de inclusão de bloqueio de Chave J que já possui bloqueio.
        requisicao = new DadosRequisicaoIncluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
        requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J1593111");
    	requisicao.setCodigoTipoBloqueioNegocial(3);
    	requisicao.setCodigoChaveUsuario("F7003009");
    	requisicao.setDataFimBloqueioNegocial(LocalDate.now().toString());
    	requisicao.setQuantidadeTextoJustificativaBloqueio(5);
    	requisicao.setTextoJustificativaBloqueioNegocial("Texto");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463945v1")
            .then()
            .statusCode(422);
    }    
}
