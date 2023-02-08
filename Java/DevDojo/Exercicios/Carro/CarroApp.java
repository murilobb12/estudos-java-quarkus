package DevDojo.Exercicios.Carro;

public class CarroApp {

    public static void main(String[] args) {

        Carro c1 = new Carro();
        Carro c2 = new Carro();

        c1.nome = "Corsa";
        c1.ano  = 2010;
        c1.modelo = "GM";

        c2.nome = "Corsa";
        c2.ano  = 2010;
        c2.modelo = "GM";

        System.out.println(c1.nome);
        System.out.println(c1.modelo);
        System.out.println(c1.ano);
        System.out.println(c2.nome);
        System.out.println(c2.modelo);
        System.out.println(c2.ano);




    }
}
