package br.com.letscode;

import java.math.BigDecimal;

public class ContaPoupanca extends ContaCorrentePF{

    public ContaPoupanca(ClientePF titular, int numeroConta) {
        super(titular, numeroConta);

    }

    public void verSaldo() {
        System.out.printf("O saldo da conta investimento do " + titular.getNome() +" é de R$%.2f \n", saldo);
    }

    public void render() {
        this.saldo = saldo.multiply(new BigDecimal("1.08"));
    }

    public void resgatar(BigDecimal resgate, Conta conta) {

        if(resgate.compareTo(saldo)!=1) {
            System.out.printf("Resgate de R$%.2f da conta investimento %d para a conta %d \n", resgate, numeroConta, conta.numeroConta );
            sacarTransferencia(resgate);
            conta.depositarTransferencia(resgate);
        }else {
            System.out.println("Aplicação negada. Saldo insuficiente.");
        }
    }
}

