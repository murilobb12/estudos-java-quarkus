package java_.EstudosMurilo.Colecoes.OrdenandoListas;

import java_.EstudosMurilo.Colecoes.Contas.ContaCorrente;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrdenandoListasStream {

    public static void main(String[] args) {

        SaldoComparator2 saldoComparator2 = new SaldoComparator2();
        NomeComparator2 nomeComparator2 = new NomeComparator2();

        ContaCorrente cc1 = new ContaCorrente(2521,234131, "Murilo");
        cc1.depositar(new BigDecimal("7000.00"));
        ContaCorrente cc2 = new ContaCorrente(1522,234132,"Marcela");
        cc2.depositar(new BigDecimal("400.00"));
        ContaCorrente cc3 = new ContaCorrente(1523,434133,"Rosana");
        cc3.depositar(new BigDecimal("500.00"));
        ContaCorrente cc4 = new ContaCorrente(1524,234134, "Gilson");
        cc4.depositar(new BigDecimal("21000.00"));
        ContaCorrente cc5 = new ContaCorrente(1524,234134, "Anaryel");
        cc5.depositar(new BigDecimal("1500.00"));

        ArrayList<ContaCorrente> contaCorrentes = new ArrayList<>();

        contaCorrentes.add(cc1);
        contaCorrentes.add(cc2);
        contaCorrentes.add(cc3);
        contaCorrentes.add(cc4);
        contaCorrentes.add(cc5);

        System.out.println("Lista desordenada");
        contaCorrentes.stream().forEach(System.out::println);

        System.out.println("Lista ordenada crescente pelo saldo");
        contaCorrentes.sort((o1, o2) -> o1.getSaldo().compareTo(o2.getSaldo()));

        contaCorrentes.stream().forEach(System.out::println);


        System.out.println("Lista ordenada decrescente pelo saldo");
        contaCorrentes.sort(saldoComparator2.reversed());
        contaCorrentes.stream().forEach(System.out::println);






    }
}
