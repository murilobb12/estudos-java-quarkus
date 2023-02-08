package DevDojo.Exercicios.Funcionario;

import aulas.Array;

public class Funcionario {

    public String nome;
    public int idade;
    public double[] salario;

    public void imprimeDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " +this.idade);
        mediaSalario();

    }

    public void mediaSalario(){
        double media = 0;
        double soma = 0;

        for (double s:salario
             ) {
            soma += s;

        }
        System.out.println(soma/ salario.length);
    }

}
