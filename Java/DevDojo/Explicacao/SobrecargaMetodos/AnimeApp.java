package DevDojo.Explicacao.SobrecargaMetodos;

public class AnimeApp {

    public static void main(String[] args) {

        Anime naruto = new Anime();
        naruto.init("Naruto", "TV", 537);

        System.out.println(naruto.imprime());

    }
}
