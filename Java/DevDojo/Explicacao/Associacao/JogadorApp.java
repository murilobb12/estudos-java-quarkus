package DevDojo.Explicacao.Associacao;

public class JogadorApp {

    public static void main(String[] args) {

        Jogador j1 = new Jogador("Murilo");
        Jogador j2 = new Jogador("Marcela");
        Jogador j3 = new Jogador("Gilson");
        Jogador j4 = new Jogador("Rosana");
        Jogador[] jogadores = {j1, j2, j3};

        for (Jogador j:jogadores
             ) {
            System.out.println(j.getNome());

        }


    }

}
