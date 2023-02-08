package org.example;

import java.security.InvalidParameterException;

public class RomeuJulieta {

    final String queijo = "Queijo";
    final String goiabada = "Goiabada";
    final String romeuEJulieta = "Romeu e Julieta";

    public String romeu_julieta(int valor) {

        if (valor <= 0){
            throw new NumberFormatException("Valor menor ou igual a zero.");
        }

        boolean div_tres = valor % 3 == 0;
        boolean div_cinco = valor % 5 == 0;
        boolean div_quinze = valor % 15 == 0;

        if (div_tres && !div_cinco) {
            return queijo;
        } else if (div_cinco && !div_tres) {
            return goiabada;
        } else if (div_quinze) {
            return romeuEJulieta;
        }

        return String.valueOf(valor);

    }


}
