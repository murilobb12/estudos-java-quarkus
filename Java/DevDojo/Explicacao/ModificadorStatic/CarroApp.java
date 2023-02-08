package DevDojo.Explicacao.ModificadorStatic;

public class CarroApp {

    public static void main(String[] args) {

        Carro c1 = new Carro("Kwid", 220);
        Carro c2 = new Carro("Mercedes", 240);
        Carro c3 = new Carro("Ferrari", 320);

        Carro.setVelocidadeLimite(123);


        c1.imprime();
        c2.imprime();
        c3.imprime();

    }



}
