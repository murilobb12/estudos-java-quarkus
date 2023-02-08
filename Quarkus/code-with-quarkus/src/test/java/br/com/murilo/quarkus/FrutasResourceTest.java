package br.com.murilo.quarkus;

import br.com.murilo.quarkus.rest.FrutasResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@TestHTTPEndpoint(FrutasResource.class)
@QuarkusTest
public class FrutasResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get()
          .then()
             .statusCode(200)
                .body(is("[{\"id\":1,\"nome\":\"Maça\",\"quantidade\":5}]"));
    }

    @Test
    public void testPostFruta(){
        given()
                .when().post()
                .then()
                .statusCode(204);
    }




}