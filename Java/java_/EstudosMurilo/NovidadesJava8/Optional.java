package java_.EstudosMurilo.NovidadesJava8;

import java.util.Arrays;
import java.util.List;

public class Optional {

    public static void main(String[] args) {


    List<Curso> cursos = Arrays.asList(new Curso("Java", 100), new Curso("Python", 50), new Curso("SQL", 20));


    cursos.stream().filter(curso -> curso.getAlunos() > 99).findAny().ifPresent(System.out::println);

    cursos.stream().filter(curso -> curso.getAlunos() > 99).findAny().orElse(null);

    }


}
