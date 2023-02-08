package java_.EstudosMurilo.Colecoes.OrdenandoListas;

import java_.EstudosMurilo.Colecoes.Contas.ContaCorrente;

import java.util.Comparator;

public class NomeComparator implements Comparator<ContaCorrente> {
    @Override
    public int compare(ContaCorrente o1, ContaCorrente o2) {
        return o1.titular.compareTo(o2.titular);
    }
}
