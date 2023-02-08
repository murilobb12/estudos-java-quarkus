package DevDojo.Exercicios.leituraTeclado;

import java.util.Random;
import java.util.Scanner;

public class LeituraTecladoApp {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random gerador = new Random();
        System.out.println("O grande software de previsão do futuro!");
        System.out.println("Digite sua pergunta e eu responderei sim ou não");

        String pergunta = sc.nextLine();
        int numeroGerado = gerador.nextInt(2);


        System.out.println((numeroGerado >= 1) ? "Sim" : "Não");





    }

}
