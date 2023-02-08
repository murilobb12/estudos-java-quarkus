package br.com.bb.cbo.operacoes;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.com.bb.cbo.operacao.listarBloqueiosNegociaisOperadorCorrespondenteBancarioV1.bean.requisicao.DadosRequisicaoListarBloqueiosNegociaisOperadorCorrespondenteBancario;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class Op6483891v1Test {
    @Test
    public void listarBloqueiosNegociaisOperador() {

        // Realizando consulta com dados inválidos.
        DadosRequisicaoListarBloqueiosNegociaisOperadorCorrespondenteBancario requisicao =
        		new DadosRequisicaoListarBloqueiosNegociaisOperadorCorrespondenteBancario();

        requisicao.setCodigoInstituicao(0);

        given()
	        .when()
	        .contentType(ContentType.JSON)
	        .body(requisicao)
	        .post("/op6483891v1")
            .then()
            .statusCode(422);
        
        // Realizando consulta com dados inválidos.
        requisicao = new DadosRequisicaoListarBloqueiosNegociaisOperadorCorrespondenteBancario();

        requisicao.setCodigoInstituicao(1);
        requisicao.setNumeroPaginaPesquisa(0);

        given()
	        .when()
	        .contentType(ContentType.JSON)
	        .body(requisicao)
	        .post("/op6483891v1")
            .then()
            .statusCode(422);
    }    
}
