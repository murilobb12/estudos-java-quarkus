package aulas;//Arrays.stream(listaNomes).forEach(s -> System.out.print(s + " "));
//Arrays.stream(listaNomes).forEach(System.out::println);

import java.util.Arrays;

public class Aula1 {
    public static void main(String[] args) {

        int[][] matrixInt = new int[4][3];
        System.out.println(matrixInt[3].length);

        String str = "Isto é uma String";
        char [] charArray = str.toCharArray();

        //For Each avançado
/*        for (char c: charArray){
            if (c == ' '){
                c = '_';
            }
            System.out.println(c);
        }*/


        //For Each Básico
/*        for (int i = 0; i < charArray.length; i++){
            if (charArray[i] == ' '){
                charArray[i] = '_';
            }
        }
        System.out.println(charArray);*/








    }
}
