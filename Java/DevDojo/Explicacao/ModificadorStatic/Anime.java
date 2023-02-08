package DevDojo.Explicacao.ModificadorStatic;

public class Anime {

    private String nome;
    private int[] episodios;

    {
        System.out.println("teste");
        episodios = new int[100];

        for (int i = 0; i < episodios.length; i++) {
            episodios[i] = i+1;

        }
    }

    public Anime() {


        for (int e : episodios
        ) {
            System.out.print(e + " ");

        }
        System.out.println();

    }
}
