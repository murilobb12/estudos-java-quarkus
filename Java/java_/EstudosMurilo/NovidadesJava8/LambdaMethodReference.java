package java_.EstudosMurilo.NovidadesJava8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class LambdaMethodReference {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();

        strings.add("Murilo");
        strings.add("Alberto");
        strings.add("Santos");

        strings.sort(/*new Comparator<String>() {
            @Override
            public int compare*/(String o1, String o2) ->
                 Integer.compare(o1.length(), o2.length()));


//        strings.sort(Comparator.comparingInt(String::length));


        //sorteando com lambda
        strings.sort(( o1, o2) -> Integer.compare(o1.length(),o2.length()));

        //Antes de aplicar lambda
        strings.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        //Apos aplicar lambda
        strings.forEach(System.out::println);
        strings.forEach(s -> System.out.println(s));


        strings.forEach(System.out::println);



        List<Curso> cursos = new ArrayList<>();

        cursos.add(new Curso("Java", 100));
        cursos.add(new Curso("Python", 150));
        cursos.add(new Curso("Javascript", 50));
        cursos.add(new Curso("SQL", 20));

        //Ordenando pelo nome sem lambda
        cursos.sort(new Comparator<Curso>() {
            @Override
            public int compare(Curso o1, Curso o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });


        //Ordenando pelo nome com lambda
        cursos.sort((o1, o2) -> o1.getNome().compareTo(o2.getNome()));
        cursos.forEach(System.out::println);





    }

}

//Criando a classe abstrata para comparar
//class ComparadorTamanho implements Comparator<String> {
//
//    @Override
//    public int compare(String s1, String s2) {
//        if (s1.length() > s2.length()) return 1;
//
//        if (s1.length() < s2.length()) return -1;
//
//        return 0;
//    }
//}
