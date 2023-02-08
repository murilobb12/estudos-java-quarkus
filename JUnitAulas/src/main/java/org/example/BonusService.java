package org.example;

import java.math.BigDecimal;

public class BonusService {


    public BigDecimal calcularBonus(Funcionario funcionario){
        BigDecimal valor = funcionario.getSalario().multiply(new BigDecimal("0.1"));
        if (valor.compareTo(new BigDecimal("1000.00")) > 0){
            valor = BigDecimal.ZERO;
        } return valor;
    }



}
