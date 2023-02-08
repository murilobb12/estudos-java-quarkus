package aulas.Julho._04;

public class App {
    public static void main(String[] args) {

        Pessoa p = new Pessoa("Murilo", 15, Pessoa.Sexo.MASCULINO);

        System.out.println(p.getSexo());

        System.out.println(p.getNome());


    }
}
