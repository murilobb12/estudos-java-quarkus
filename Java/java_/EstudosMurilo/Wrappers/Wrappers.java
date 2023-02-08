package java_.EstudosMurilo.Wrappers;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Serve para guardar os tipos primitivos e utilizarmos nas listas
 *
 */
public class Wrappers {


    public static void main(String[] args) {

        int[] idades = new int[2];
        String[] nomes = new String[2];


        String nome = "82";
        int idade = 27;

        List<Integer> numeros = new ArrayList<>();

        System.out.println(idade + Integer.valueOf(nome));
        System.out.println(idade + Integer.parseInt(nome));

        numeros.add(idade);

        System.out.println(numeros);



    }



}
