package aulas.Julho._27solID;

public class App {

    public static void main(String[] args) {

        Desenvolvedor java = new DesenvolvedorJava();

        Desenvolvedor cobol = new DesenvolvedorCobol();

//        Desenvolvedor cobol = new DesenvolvedorCobol();

        ServicoDesenvolvedor sd = new ServicoDesenvolvedor(cobol);

        sd.desenvolvedorATrabalhar();


    }

}
