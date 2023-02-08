package aulas.Julho._27solID;

public class DesenvolvedorCobol implements DesenvolvedorBE{


    @Override
    public void programar(int horas) {
        System.out.println("Escrevi código Cobol durante" + horas + "horas ");
    }

    @Override
    public void desenhaBD() {
        System.out.println("Estou a desenhar o banco de dados.");
    }
}
