package com.letscode.ecommerce.restClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
        value = "financeiroRestClient",
        url = "https://dummyjson.com")
public interface FinanceiroRestClient {

    @GetMapping(path="/products/categories", produces = APPLICATION_JSON_VALUE)
    List<String> findAll();
}

