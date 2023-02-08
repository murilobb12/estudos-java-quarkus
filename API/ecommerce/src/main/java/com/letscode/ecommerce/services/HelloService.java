package com.letscode.ecommerce.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public interface HelloService {

    Logger log = LoggerFactory.getLogger(HelloService.class);

    public String getHorario();


}
