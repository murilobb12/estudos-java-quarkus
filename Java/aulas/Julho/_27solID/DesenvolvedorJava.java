package aulas.Julho._27solID;

public class DesenvolvedorJava implements DesenvolvedorFE, DesenvolvedorBE{


    @Override
    public void programar(int horas) {
        System.out.println("Escrevi código Java durante" + horas + "horas");
    }

    @Override
    public void desenhaBD() {
        System.out.println("Estou a desenhar o banco de dados.");
    }

    @Override
    public void desenhaEcra() {
        System.out.println("Estou a desenhar o ecrã");
    }
}
