import java.io.IOException;

public class TestarToken {

    public static void main(String[] args) throws IOException, InterruptedException {
        GerarTokenBndes tokenBndes = new GerarTokenBndes();

        System.out.println(tokenBndes.gerarToken());

    }

}
