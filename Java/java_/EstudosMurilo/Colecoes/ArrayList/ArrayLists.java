package java_.EstudosMurilo.Colecoes.ArrayList;

import java_.EstudosMurilo.Colecoes.Contas.ContaCorrente;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *   Muito utilizado no java moderno, devido a sua facildade em implementar as suas funlções oriundas da classe Collection
 *   Pontos negativos: muito ruim quando a lista sofre diversas remoções no início e no meio.
 *   @author F7287356
 *
 */
public class ArrayLists {

    public static void main(String[] args) {

        ArrayList<ContaCorrente> listaContas = new ArrayList();

        ContaCorrente cc1 = new ContaCorrente(2201,23413);
        listaContas.add(cc1);

        ContaCorrente cc2 = new ContaCorrente(2201,23413);


        listaContas.stream().forEach(System.out::println);

        //A conta existe pois foi implementado o Equals das Contas Correntes
        boolean existe = listaContas.contains(cc2);

        System.out.println("A conta existe? " + existe);


        List<String> stringList = new ArrayList<>();

        stringList.add("Teste");
        stringList.add("Teste");
        stringList.add("Teste");


    }

}
