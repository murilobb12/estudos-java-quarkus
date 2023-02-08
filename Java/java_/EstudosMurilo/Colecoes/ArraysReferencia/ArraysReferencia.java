package java_.EstudosMurilo.Colecoes.ArraysReferencia;

import java_.EstudosMurilo.Colecoes.Contas.ContaCorrente;

public class ArraysReferencia {

    public static void main(String[] args) {

        ContaCorrente[] contasCorrentes = new ContaCorrente[5];
        ContaCorrente[] contaCorrentes2 = {new ContaCorrente(1,2), new ContaCorrente(5,6)};

        ContaCorrente cc1 = new ContaCorrente(1521, 23413 );

        contasCorrentes[1] = cc1;

        for (ContaCorrente cc: contasCorrentes
             ) {
            if (cc == null){
                cc = new ContaCorrente(0,0);
            }
            System.out.println(cc);
        }

    }




}
