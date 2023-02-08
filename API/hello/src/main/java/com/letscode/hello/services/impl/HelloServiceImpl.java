package com.letscode.hello.services.impl;

import com.letscode.hello.services.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class HelloServiceImpl implements HelloService {

    Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String getHorario() {

        log.info("Mensagem exibida com sucesso.");

        LocalTime localTime = LocalTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return localTime.format(formatter);
    }
}
