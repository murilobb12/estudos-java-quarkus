package DevDojo.Exercicios;

public class UtilFinalSemana {

    public static void main(String[] args) {

        byte diaSemana = 1;

        switch (diaSemana){
            case 1:
            case 7:
                System.out.println("Final de semana");
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                System.out.println("Dia útila!");
                break;
        }

    }

}
