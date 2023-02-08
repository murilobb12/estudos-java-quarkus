package java_.EstudosMurilo.Colecoes.HashSet;

import java_.EstudosMurilo.Colecoes.Contas.ContaCorrente;

import java.util.Collection;
import java.util.HashSet;


/**
 *
 * HashSet serve para não deixar entrar itens duplicados na lista
 *
 */
public class HashSets {

    public static void main(String[] args) {

        Collection<ContaCorrente> collection = new HashSet<>();

        ContaCorrente cc1 = new ContaCorrente(1521,23413);
        ContaCorrente cc2 = new ContaCorrente(1521, 23413);
        collection.add(cc1);
        collection.add(cc2);

        System.out.println(collection);




    }


}
