package java_.EstudosMurilo.Colecoes.ArrayAdapter;

import java_.EstudosMurilo.Colecoes.Contas.ContaCorrente;

public class ArrayAdapter {

    public static void main(String[] args) {

        GuardadorDeContas guardadorDeContas = new GuardadorDeContas();

        ContaCorrente cc1 = new ContaCorrente(2201,234133);
        ContaCorrente cc2 = new ContaCorrente(2202,234132);
        ContaCorrente cc3 = new ContaCorrente(2203,234133);
        ContaCorrente cc4 = new ContaCorrente(2204,234134);

        guardadorDeContas.adiciona(cc1);
        guardadorDeContas.adiciona(cc2);
        guardadorDeContas.adiciona(cc3);
        guardadorDeContas.adiciona(cc4);

        System.out.println(guardadorDeContas);
        System.out.println(guardadorDeContas.getQuantidadeDeElementos());
        System.out.println(guardadorDeContas.getConta(0).getNumero());


    }

}
