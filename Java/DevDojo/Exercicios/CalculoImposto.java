package DevDojo.Exercicios;

import java.math.BigDecimal;
import java.util.Scanner;

public class CalculoImposto {

    public static void main(String[] args) {



        BigDecimal salarioCalculado = new BigDecimal(0);

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o seu salário");
        BigDecimal salario = sc.nextBigDecimal();

        System.out.println(salario);

        if (salario.compareTo(new BigDecimal("34.712")) == -1){
            salarioCalculado = salario.multiply(new BigDecimal(0.097));
            System.out.println(salarioCalculado);
            System.out.println("range 0 ate 34");
        } else if (salario.compareTo(new BigDecimal("34.712")) == 1 && salario.compareTo(new BigDecimal("68.508")) == -1) {
            salarioCalculado = salario.multiply(new BigDecimal(0.0375));
            System.out.println("range 34 ate 68");
        }else {
            System.out.println("range maior que 68");
        }

    }

}
