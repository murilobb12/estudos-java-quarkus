package aulas;

import java.math.BigDecimal;

public class Teste {

    public void printInteresForUser(String username, double balance, double interesRate){
        System.out.printf("O %s irá receber R$ (%n/%s)", username, balance, interesRate);
    };

    public static void main(String[] args) {

        System.out.println(new BigDecimal("25.00"));

        final String username = "João";
        final double balance = 1000;
        final double interestRate = 12.5;

        Teste q1 = new Teste();

        q1.printInteresForUser(username, balance,interestRate );


    }
}
