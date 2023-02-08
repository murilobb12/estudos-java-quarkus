package br.com.murilo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BitcoinTest {


    @Test
    public void testeBitcoin(){
        RestAssured
                .given().get("bitcoins").then().statusCode(200)
                .body(is("[{\"data\":\"2022-10-24\",\"id\":1,\"preco\":500.0,\"tipo\":\"COMPRA\"},{\"data\":\"2022-10-24\",\"id\":2,\"preco\":30.0,\"tipo\":\"COMPRA\"},{\"data\":\"2022-10-24\",\"id\":3,\"preco\":350.0,\"tipo\":\"COMPRA\"},{\"data\":\"2022-10-24\",\"id\":4,\"preco\":15.0,\"tipo\":\"COMPRA\"},{\"data\":\"2022-10-24\",\"id\":5,\"preco\":900.0,\"tipo\":\"VENDA\"},{\"data\":\"2022-10-24\",\"id\":6,\"preco\":200.0,\"tipo\":\"VENDA\"},{\"data\":\"2022-10-24\",\"id\":7,\"preco\":5.0,\"tipo\":\"VENDA\"},{\"data\":\"2022-10-24\",\"id\":8,\"preco\":150.0,\"tipo\":\"COMPRA\"},{\"data\":\"2022-10-24\",\"id\":9,\"preco\":150.0,\"tipo\":\"COMPRA\"}]"));

    }



}
