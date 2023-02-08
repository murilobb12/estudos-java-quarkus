package org.example;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ReajusteService reajusteService = new ReajusteService();

        Funcionario murilo = new Funcionario("Murilo", new BigDecimal("7000.00"), Desempenho.A);

        System.out.println(murilo);

        System.out.println("Realizando reajuste salarial conforme o desempenho.");
        murilo.setSalario(reajusteService.reajusteSalarial(murilo));

        System.out.println("Novo salário conforme o reajuste.");
        System.out.println(murilo);





    }
}