package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        BonusService bonusService = new BonusService();
        Funcionario funcionario = new Funcionario("Murilo", LocalDate.now(), new BigDecimal("5000.00"));

        BigDecimal valorBonus = bonusService.calcularBonus(funcionario);

        System.out.println(valorBonus);



    }
}