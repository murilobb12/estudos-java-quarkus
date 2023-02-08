package DevDojo.Explicacao.Associacao;

public class JogadorTimeApp {

    public static void main(String[] args) {

        Jogador jogador1 = new Jogador("Murilo");
        Time time = new Time("Corinthians");

        jogador1.setTime(time);

//        System.out.println(jogador1.getNome());
//        System.out.println(jogador1.getTime());

//        jogador1.imprime();

        System.out.println(jogador1.getTime().getNome());

    }

}
