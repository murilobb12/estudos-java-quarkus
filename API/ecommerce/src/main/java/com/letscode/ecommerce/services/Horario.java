package com.letscode.ecommerce.services;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class Horario {




    public String getHorario() {
        LocalTime localTime = LocalTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return localTime.format(formatter);
    }
}
