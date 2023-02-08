package DevDojo.Exercicios.Funcionario;

public class FuncionarioApp {

    public static void main(String[] args) {

        Funcionario f = new Funcionario();

         f.nome = "Murilo";
         f.idade = 27;
         f.salario = new double[]{2000  ,2000,1000};

         f.imprimeDados();

//        f.mediaSalario(f.salario);
    }


}
