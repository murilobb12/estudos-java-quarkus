package DevDojo.Explicacao.Introducao;

public class OperadorTernario {
    public static void main(String[] args) {

        double salario = 200;

        String mensagemDoar = "Eu vou doar 500 pro Murilo";
        String mensagemNaoDoar = "Ainda não tenho condições para doar";

        String resultado = (salario>5000) ? mensagemDoar : mensagemNaoDoar;

        System.out.println(resultado);


    }

}
