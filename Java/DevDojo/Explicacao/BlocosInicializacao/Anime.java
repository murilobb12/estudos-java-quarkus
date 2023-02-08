package DevDojo.Explicacao.BlocosInicializacao;

public class Anime {

    private String nome;
    private int[] episodios;

    {
        System.out.println("teste");
    }

    public Anime() {
        episodios = new int[100];

        for (int i = 0; i < episodios.length; i++) {
            episodios[i] = i+1;

        }

        for (int e : episodios
        ) {
            System.out.print(e + " ");

        }

    }
}
