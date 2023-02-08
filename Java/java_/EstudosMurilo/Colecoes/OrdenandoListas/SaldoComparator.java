package java_.EstudosMurilo.Colecoes.OrdenandoListas;

import java_.EstudosMurilo.Colecoes.Contas.ContaCorrente;

import java.util.Comparator;

public class SaldoComparator implements Comparator<ContaCorrente> {
    @Override
    public int compare(ContaCorrente o1, ContaCorrente o2) {
        return o1.getSaldo().compareTo(o2.getSaldo());
        }
//        return Integer.compare(o1.getSaldo(), o2.getSaldo());

//    @Override
//    public Comparator<ContaCorrente> reversed() {
//        return Comparator.super.reversed();
//    }
}
