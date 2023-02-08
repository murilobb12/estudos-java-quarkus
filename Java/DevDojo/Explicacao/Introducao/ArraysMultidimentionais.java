package DevDojo.Explicacao.Introducao;

public class ArraysMultidimentionais {


    public static void main(String[] args) {

        int teste[][] = new int[2][2];

        for (int i = 0; i < teste.length; i++) {
            for (int j = 0; j < teste[i].length; j++) {
                System.out.println(teste[i][j]);
            }
        }

        System.out.println("-*-----------------**-*-*-*--**-*-");
        for (int[] array: teste) {
            for (int i:array) {
                System.out.println(i);
            }

        }

    }

}
