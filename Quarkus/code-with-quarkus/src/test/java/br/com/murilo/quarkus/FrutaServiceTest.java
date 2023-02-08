package br.com.murilo.quarkus;

import br.com.murilo.quarkus.service.FrutaService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;

@TestHTTPEndpoint(FrutaService.class)
@QuarkusTest
public class FrutaServiceTest {


    @Inject
    FrutaService frutaService;

    public void insertFrutasTest(){



    }

}
