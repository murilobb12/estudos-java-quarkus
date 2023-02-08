package aulas.Julho._11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
//        System.out.println("Hello World");
//
//        String[] names = {"Murilo", "Pedro", "Ana"};
//        System.out.println(names[2]);

//        int result = 10 /0;
//
//        System.out.println(result);

//        Integer number =  null;
//        System.out.println(number.doubleValue());

        try {
            new FileInputStream(" ");
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao abrir o arquivo MSG:" + e.getMessage());
        }


    }
}
