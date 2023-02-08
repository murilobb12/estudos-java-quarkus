package java_.EstudosMurilo.Colecoes.LinkedList;

import java_.EstudosMurilo.Colecoes.Contas.ContaCorrente;

import java.util.LinkedList;

/**
 *
 *
 * Pontos positivos: muito bom quando temos diversas remoções no início da lista.
 * Pontos negativos: ruim quando queremos ver o último item da lista, muito ruim fazer iterações
 *
 * @author F7287356
 *
 */

public class LinkedLists {


    public static void main(String[] args) {

        LinkedList<ContaCorrente> listaContas = new LinkedList<>();

        listaContas.add(new ContaCorrente(1521,23413));

        listaContas.stream().forEach(System.out::println);







    }


}
