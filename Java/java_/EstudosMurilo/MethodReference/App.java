package java_.EstudosMurilo.MethodReference;

import aulas.Array;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        //Sem Method Reference
        list.stream()
                .forEach(s -> System.out.println(s));

        //Com MethodReference
        list.stream()
                .forEach(System.out::println);

        //Métodos Estáticos
        //Sem Method Reference
        list.stream()
                .map(i -> multiplicaPorDois(i))
                .forEach(System.out::println);

        list.stream()
                .map(App::multiplicaPorDois)
                .forEach(System.out::println);

        //Construtores
        //Sem method Reference
        list.stream()
                .map(i -> new BigDecimal(i))
                .forEach(System.out::println);

        //Com method Reference
        list.stream()
                .map(BigDecimal::new)
                .forEach(System.out::println);

        //Várias Instâncias
        //Sem Method Reference
        list.stream()
                .map(s -> s.doubleValue() )
                .forEach(System.out::println);

        //Com Method Reference
        list.stream()
                .map(Integer::doubleValue)
                .forEach(System.out::println);



    }

    private static Integer multiplicaPorDois(Integer i){
        return i*2;
    }

}
