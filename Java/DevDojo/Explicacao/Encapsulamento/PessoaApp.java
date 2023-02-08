package DevDojo.Explicacao.Encapsulamento;

public class PessoaApp {

    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa();

        pessoa.setIdade(10);
        pessoa.setNome("Murilo");
        System.out.println(pessoa.getNome());
        System.out.println(pessoa.getIdade());



    }
}
