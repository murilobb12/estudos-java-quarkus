package org.example;



public class Main {
    public static void main(String[] args) {

        RomeuJulieta romeuJulieta = new RomeuJulieta();
        System.out.println("Bem vindo ao programa Romeu e Julieta!");
        System.out.println();
        System.out.println("0 - É preciso ter algo para dividir!");

        for (int i = 1; i <= 100 ; i++) {
            System.out.println(i + " - " + romeuJulieta.romeu_julieta(i));
        }

    }

}