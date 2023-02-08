package java_.EstudosMurilo.NovidadesJava8;

import java.beans.Customizer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToMap {

    public static void main(String[] args) {


/*        List<Curso> cursos = Arrays.asList(new Curso("Java", 100), new Curso("Python", 50), new Curso("SQL", 20));


        cursos.stream().filter(curso -> curso.getAlunos() > 20).collect(Collectors.toMap(curso -> curso.getNome(), o -> o.getAlunos())).forEach((s, alunos) -> System.out.println(s + " tem " + alunos));
        Map<String, Integer> teste = cursos.stream().filter(curso -> curso.getAlunos() > 20).collect(Collectors.toMap(curso -> curso.getNome(), o -> o.getAlunos()));

        */

        List<Curso> cursos = Arrays.asList(new Curso("Python", 50,5), new Curso("SQL", 100,40), new Curso("Java", 500,999));

        //Pegar a list cursos, filtrar por alunos menor que 20, transformar em mapa passando o nome e o número de alunos, e imprimir da seguinte maneira "Curso" tem "qtd_alunos";
        cursos.stream().filter(new Predicate<Curso>() {
            @Override
            public boolean test(Curso curso) {
                return curso.getAlunos() > 20;
            }
        }).collect(Collectors.toMap(curso -> curso.getNome(),curso -> curso.getAlunos())).forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s + " tem: " + integer);
            }
        });

        //Pegar o primeiro item filtrado do stream
        cursos.stream().filter(curso -> curso.getAlunos() > 20).findFirst().ifPresent(System.out::println);

        //Calcular a media de todos os alunos cadastrados
        OptionalDouble media = cursos.stream().mapToDouble(curso -> curso.getAlunos()).average();
        System.out.println(media);

        Stream<Curso> stream = cursos.stream().filter(c -> c.getAlunos() > 50);

        List<Curso> cursoList = stream.collect(Collectors.toList());





    }

}
