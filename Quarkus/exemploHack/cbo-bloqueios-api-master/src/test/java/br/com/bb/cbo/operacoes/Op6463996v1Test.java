package br.com.bb.cbo.operacoes;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.com.bb.cbo.operacao.excluirBloqueioNegocialOperadorCorrespondenteBancarioV1.bean.requisicao.DadosRequisicaoExcluirBloqueioNegocialOperadorCorrespondenteBancario;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class Op6463996v1Test {
    @Test
    public void excluirBloqueioNegocialOperador() {
    	
    	DadosRequisicaoExcluirBloqueioNegocialOperadorCorrespondenteBancario requisicao =
    			new DadosRequisicaoExcluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J4561293");
    	requisicao.setCodigoChaveUsuario("F7003009");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463996v1")
            .then()
            .statusCode(200);
        
        // Teste de exclusão de bloqueio com dados inválidos.
        requisicao = new DadosRequisicaoExcluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(0);
    	requisicao.setCodigoIdentificacaoCorrespondente(null);
    	requisicao.setCodigoChaveUsuario("F7003009");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463996v1")
            .then()
            .statusCode(422);
        
        // Teste de exclusão de bloqueio de Chave J inexistente.
        requisicao = new DadosRequisicaoExcluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J45612");
    	requisicao.setCodigoChaveUsuario("F7003009");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463996v1")
            .then()
            .statusCode(422);
        
        // Teste de exclusão de bloqueio de Chave J que não possui bloqueio.
        requisicao = new DadosRequisicaoExcluirBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J9865147");
    	requisicao.setCodigoChaveUsuario("F7003009");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6463996v1")
            .then()
            .statusCode(422);
    }    
}
