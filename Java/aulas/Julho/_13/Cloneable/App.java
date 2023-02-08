package aulas.Julho._13.Cloneable;

import java.util.Arrays;

public class App {

    public static void main( String[] args ) throws CloneNotSupportedException {
        final Pessoa p1 = new Pessoa("Nuno", "Passos", 36);
        //p1.criarCartão(563802);
//        System.out.println(p1);
        final Pessoa p2 = p1.criarClone();
        //System.out.println(p2);

        final Pessoa p3 = new Pessoa("Alexandre", "Xavier", 47);
        final Pessoa p4 = new Pessoa("Beatriz", "Matos", 24);
        final Pessoa p5 = new Pessoa("Bruno", "Andrade", 19);
        final Pessoa p6 = new Pessoa("Filipe", "Silva", 52);

        Pessoa[] listaPessoas = { p1, p2, p3, p4, p5, p6};
        Arrays.sort(listaPessoas);
        for (Pessoa p : listaPessoas) {
            System.out.println(p);

        }
    }

}