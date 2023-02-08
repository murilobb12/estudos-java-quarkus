package br.com.murilo.service;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ApplicationScoped
public class GerarToken {

    public String gerarToken() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://apis-gateway-h.bndes.gov.br/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic cWVVV3JxMGZrNWVtU2FxakgwSTlUVXFzQ3dzYTpPeGlnRlYyMUZpb1ZvMEJlMENnbXBVU01nY1Vh")
                .method("POST", HttpRequest.BodyPublishers.ofString("grant_type=client_credentials&scope=fg2_read%20fg2_write"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        int posInicial = responseBody.indexOf(":") + 2;
        int posFinal = responseBody.indexOf(":", 16) - 9;

        String bearerToken = "Bearer " + responseBody.substring(posInicial, posFinal);

        return bearerToken;
    }

}
