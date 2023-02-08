package DevDojo.Exercicios;

import java.util.Scanner;

public class ParcelasCarro {
    //Dada o valor de um carro, descubra em quantas ele pode ser parcelado
    //Condição valorParcela >= 100

    public static void main(String[] args) {

        double valorCarro = 50000.00;


        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o valor do carro:");

        for (int parcela = 1; parcela <= valorCarro;parcela++){
            double valorParcela = valorCarro / parcela;
            if (valorParcela>=1000){
                System.out.println(valorParcela + "Quantidade de parcelas: " + parcela);
            }

        }

    }

}
