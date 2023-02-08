package br.com.murilo.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {

    public static String print(){
        return "Hello from the sky!";
    }

}
