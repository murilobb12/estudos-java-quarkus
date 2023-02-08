package br.com.bb.cbo.operacoes;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.com.bb.cbo.operacao.consultarBloqueioNegocialOperadorCorrespondenteBancarioV1.bean.requisicao.DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class Op6454625v1Test {
    @Test
    public void consultarBloqueioNegocialOperador() {

        // Consultando uma Chave J com bloqueio.
    	DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario requisicao =
    			new DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J1234567");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6454625v1")
            .then()
            .statusCode(200);
        
        // Consultando uma Chave J sem bloqueio.
        requisicao = new DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J9865147");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6454625v1")
            .then()
            .statusCode(422);
        
        // Consultando uma Chave J inexistente.
        requisicao = new DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J98");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6454625v1")
            .then()
            .statusCode(422);

        // Realizando consulta com dados inválidos.
        requisicao = new DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario();

        requisicao.setCodigoInstituicao(0);
        requisicao.setCodigoIdentificacaoCorrespondente(null);

        given()
	        .when()
	        .contentType(ContentType.JSON)
	        .body(requisicao)
	        .post("/op6454625v1")
            .then()
            .statusCode(422);
        
        // Realizando consulta com dados inválidos.
        requisicao = new DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario();

        requisicao.setCodigoInstituicao(1);
        requisicao.setCodigoIdentificacaoCorrespondente("J123456789789");

        given()
	        .when()
	        .contentType(ContentType.JSON)
	        .body(requisicao)
	        .post("/op6454625v1")
            .then()
            .statusCode(422);
        
        // Teste de consulta para Chave J com Gestor de Rede inexistente na tabela DB2CBO.GST_REDE_CRS_BCRO.
        requisicao = new DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario();

        requisicao.setCodigoInstituicao(1);
        requisicao.setCodigoIdentificacaoCorrespondente("J1654821");

        given()
	        .when()
	        .contentType(ContentType.JSON)
	        .body(requisicao)
	        .post("/op6454625v1")
            .then()
            .statusCode(422);
        
        // Teste de consulta para Chave J com Correspondente Bancário inexistente na tabela DB2CBO.HST_CRS_BCRO.
        requisicao = new DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario();

        requisicao.setCodigoInstituicao(1);
        requisicao.setCodigoIdentificacaoCorrespondente("J1654829");

        given()
	        .when()
	        .contentType(ContentType.JSON)
	        .body(requisicao)
	        .post("/op6454625v1")
            .then()
            .statusCode(422);
        
        // Teste de consulta para Chave J com Correspondente Bancário inexistente na tabela DB2CBO.CRS_BCRO.
        requisicao = new DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario();

        requisicao.setCodigoInstituicao(1);
        requisicao.setCodigoIdentificacaoCorrespondente("J1654711");

        given()
	        .when()
	        .contentType(ContentType.JSON)
	        .body(requisicao)
	        .post("/op6454625v1")
            .then()
            .statusCode(422);
        
        // Teste de consulta para Chave J com bloqueio, mas sem histórico na tabela DB2CBO.HST_IDFC_ACSS_CRS.
    	requisicao = new DadosRequisicaoConsultarBloqueioNegocialOperadorCorrespondenteBancario();
    	
    	requisicao.setCodigoInstituicao(1);
    	requisicao.setCodigoIdentificacaoCorrespondente("J1234568");
    	
        given()
            .when()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .post("/op6454625v1")
            .then()
            .statusCode(422);
    }    
}
