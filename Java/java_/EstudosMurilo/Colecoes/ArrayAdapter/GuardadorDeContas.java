package java_.EstudosMurilo.Colecoes.ArrayAdapter;

import java_.EstudosMurilo.Colecoes.Contas.ContaCorrente;

import java.util.Arrays;

public class GuardadorDeContas {

    private ContaCorrente[] referencias;
    private int posicaoLivre;

    public GuardadorDeContas() {
        this.referencias = new ContaCorrente[10];
    }


    public void adiciona(ContaCorrente conta) {
        this.referencias[posicaoLivre] = conta;
        posicaoLivre++;
    }


    public int getQuantidadeDeElementos(){
        return posicaoLivre;
    }

    public ContaCorrente getConta(int posicaoConta){
        return this.referencias[posicaoConta];
    }

    @Override
    public String toString() {
        return "GuardadorDeContas{" +
                "referencias=" + Arrays.toString(referencias) +
                '}';
    }

}
