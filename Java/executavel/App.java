package executavel;

import java.util.*;
import java.util.stream.Stream;


public class App {

    public static void main(String[] args) {


        List<Curso3> curso3s = Arrays.asList(new Curso3("Teste", 123),new Curso3("Teste 2", 321),new Curso3("Teste 3", 666));


        curso3s.sort(Comparator.comparingInt(Curso3::getAlunos));

        curso3s.stream().filter(curso3 -> curso3.getAlunos() > 500).forEach(curso -> System.out.println(curso.getNome()));

        Stream<String> stringStream = curso3s.stream().map(Curso3::getNome);

        stringStream.forEach(System.out::println);


        Optional<Curso3> anyCourse = curso3s.stream().filter(curso3 -> curso3.getAlunos() > 500).findAny();

        System.out.println(anyCourse);

    }

}

class Curso3{

    private String nome;
    private int alunos;

    public Curso3(String nome, int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getAlunos() {
        return alunos;
    }

    public void setAlunos(int alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Curso3{" +
                "nome='" + nome + '\'' +
                ", alunos=" + alunos +
                '}';
    }
}
