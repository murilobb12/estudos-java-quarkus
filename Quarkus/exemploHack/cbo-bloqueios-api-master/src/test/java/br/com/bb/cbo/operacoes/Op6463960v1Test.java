package br.com.bb.cbo.operacoes;

import static io.restassured.RestAssured.given;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import br.com.bb.cbo.operacao.alterarBloqueioNegocialOperadorCorrespondenteBancarioV1.bean.requisicao.DadosRequisicaoAlterarBloqueioNegocialOperadorCorrespondenteBancario;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class Op6463960v1Test {
    @Test
    public void alterarBloqueioNegocialOperador() {
    	
    	DadosRequisicaoAlterarBloqueioNegocialOperadorCorrespondenteBancario requisicao =
    			new DadosRequisicaoAlterarBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J7894516");
    	requisicao.setCodigoChaveUsuario("F7003009");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463960v1")
            .then()
            .statusCode(200);
        
        // Teste de alteração com dados inválidos.
        requisicao = new DadosRequisicaoAlterarBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(0);
    	requisicao.setCodigoIdentificacaoCorrespondente("J7894516");
    	requisicao.setCodigoChaveUsuario("F7003009");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463960v1")
            .then()
            .statusCode(422);
        
        // Teste de alteração de Chave J inexistente.
        requisicao = new DadosRequisicaoAlterarBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J4516");
    	requisicao.setCodigoChaveUsuario("F7003009");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463960v1")
            .then()
            .statusCode(422);
        
        // Teste de alteração de Chave J sem bloqueio.
        requisicao = new DadosRequisicaoAlterarBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J9865147");
    	requisicao.setCodigoChaveUsuario("F7003009");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463960v1")
            .then()
            .statusCode(422);
        
        // Teste de alteração de Chave J sem alteração dos dados.
        requisicao = new DadosRequisicaoAlterarBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J7894516");
    	requisicao.setCodigoChaveUsuario("F7003009");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463960v1")
            .then()
            .statusCode(422);
        
        // Teste de alteração de Chave J com data anterior à data atual.
        requisicao = new DadosRequisicaoAlterarBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J7894516");
    	requisicao.setCodigoChaveUsuario("F7003009");
    	requisicao.setDataFimBloqueioNegocial(LocalDate.now().minusDays(1).toString());
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463960v1")
            .then()
            .statusCode(422);
    }    
}
