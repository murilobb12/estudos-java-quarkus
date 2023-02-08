package com.api.parkingcontrol.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingEndpoints {

    /**
     * @return Hello World
     */
    @GetMapping("/")
    public String index(){
        return "Hello World";
    }


    @GetMapping("/{nome}")
    public String seuNome(@PathVariable String nome){
        return ("Olá: " + nome) ;
    }




}
