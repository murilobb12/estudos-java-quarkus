package java_.EstudosMurilo.NovidadesJava8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class Explicacao {

    public static void main(String[] args) {

        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso("Java", 50));
        cursos.add(new Curso("Python", 100));
        cursos.add(new Curso("SQL", 20));
        cursos.add(new Curso("Javascript", 40));

        //Sorteando sem Method Reference
        cursos.sort(new Comparator<Curso>() {
            @Override
            public int compare(Curso o1, Curso o2) {
                return Integer.compare(o1.getAlunos(), o2.getAlunos());
            }
        });

        //Sorteando com Method Reference
        cursos.sort(Comparator.comparingInt(Curso::getAlunos));


        //Usando um forEach com Consumer
        cursos.forEach(new Consumer<Curso>() {
            @Override
            public void accept(Curso curso) {
                System.out.println(curso.getNome() + " " + curso.getAlunos());
            }
        });

        //Usando um forEach com Method Reference
        cursos.forEach(System.out::println);


    }


}


class Curso {

    private String nome;
    private int alunos;
    private int tempo;

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public Curso(String nome, int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }

    public Curso(String nome, int alunos, int tempo) {
        this.nome = nome;
        this.alunos = alunos;
        this.tempo = tempo;
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
        return "Curso{" + "nome='" + nome + '\'' + ", alunos=" + alunos + '}';
    }
}
