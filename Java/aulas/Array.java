package aulas;

import javax.swing.*;

public class Array {
    public static void main(String[] args) {
        String[] nomes = new String[5];
        int maior = 0;
        int indicepalavra=0;

        int tamanho = nomes.length;

        for (int i = 0; i < tamanho; i++) {
            nomes[i] = JOptionPane.showInputDialog("Digite o " + (i + 1) + "º nome");
        }
        for (int i = 0; i < tamanho; i++) {
            if (maior < nomes[i].length()) {
                maior = nomes[i].length();
                indicepalavra = i;
            }
        }
        JOptionPane.showMessageDialog(null, nomes[indicepalavra]);
    }
}