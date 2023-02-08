package java_.EstudosMurilo.NovidadesJava8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class Streams {

    public static void main(String[] args) {

        List<Curso> cursos = Arrays.asList(new Curso("Java", 100), new Curso("Python", 50), new Curso("SQL", 20));

        //Aplicando filtro na stream com lambda
        cursos.stream().filter(curso -> curso.getAlunos() > 20).mapToInt(new ToIntFunction<Curso>() {
            @Override
            public int applyAsInt(Curso value) {
                return value.getAlunos();
            }
        }).forEach(System.out::println);

        //Aplicando filtro e map com lambda
        cursos.stream().filter(curso -> curso.getAlunos() > 30).map(curso -> curso.getAlunos()).forEach(System.out::println);

        int soma = cursos.stream().mapToInt(Curso::getAlunos).filter(alunos -> alunos > 20).sum();
        System.out.println(soma);

        //Aplicando filtro na stream sem lambda
        cursos.stream().filter(new Predicate<Curso>() {
            @Override
            public boolean test(Curso curso) {
                return curso.getAlunos() > 21;
            }
        }).forEach(System.out::println);


    }

}
