package com.letscode.hello.endpoints;

import com.letscode.hello.services.HelloService;
import com.letscode.hello.services.impl.HelloServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloEndpoints {

    Logger log = LoggerFactory.getLogger(HelloEndpoints.class);

    @Autowired
    HelloService helloService;

    @RequestMapping(path = "hello", method = RequestMethod.GET)
    public ResponseEntity hello(){
        log.info("Mensagem exibida com sucesso.");
        return new ResponseEntity("Hello World!", HttpStatus.OK);
    }

    @RequestMapping(path = "/hello/{nome}" , method = RequestMethod.GET)
    public ResponseEntity<String> helloNome(@PathVariable String nome){
        log.info("Mensagem exibida com sucesso.");
        return new ResponseEntity<String>("Hello " + nome + "!", HttpStatus.OK);
    }

    @RequestMapping(path = "/hello/{nome}/horario" , method = RequestMethod.GET)
    public ResponseEntity<String> helloWorld(@PathVariable String nome){
        return new ResponseEntity<String>("Hello " + nome + "! Agora são " + helloService.getHorario() , HttpStatus.OK);
    }

}
