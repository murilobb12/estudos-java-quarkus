package java_.EstudosMurilo.NovidadesJava8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamToList {

    public static void main(String[] args) {


        List<Curso> cursos = Arrays.asList(new Curso("Java", 100), new Curso("Python", 50), new Curso("SQL", 20));

         cursos = cursos.stream().filter(curso -> curso.getAlunos() > 99).collect(Collectors.toList());

         cursos.forEach(System.out::println);


    }


}
