package br.com.letscode;

import java.math.BigDecimal;

public class ContaCorrentePJ extends Conta{

    private BigDecimal taxa;

    public ContaCorrentePJ(ClientePJ titular, int numeroConta) {
        super(titular, numeroConta);

    }

    @Override
    public void sacar(BigDecimal saque) {
            taxa =saque.multiply(new BigDecimal("0.005"));
        if(saque.compareTo(saldo)!=1 && saque.add(taxa).compareTo(saldo) != 1) {
            this.saldo = saldo.subtract(saque);
            this.saldo = saldo.subtract(taxa);
            System.out.printf("Foi realizado com sucesso o saque de R$%.2f \n", saque);
            System.out.printf("Foi descontada uma taxa de R$%.2f \n", taxa);

        }else {
            System.out.printf("Saldo insuficiente. Saldo:%.2f  Valor saque: R$%.2f\n", this.saldo, saque.add(taxa) );
        }
    }

    @Override
    public void tranferir(BigDecimal transferencia, Conta conta) {
        taxa =transferencia.multiply(new BigDecimal("0.005"));
        transferencia = transferencia.add(taxa);
        if(transferencia.compareTo(saldo)!=1) {
            System.out.printf("Transferência de R$%.2f da conta %d para a conta %d \n", transferencia, numeroConta, conta.numeroConta );
            this.saldo = saldo.subtract(taxa);
            sacarTransferencia(transferencia);
            conta.depositarTransferencia(transferencia);
            System.out.printf("Foi descontada uma taxa de R$%.2f \n", taxa);
        }else {
            System.out.println("Transferência negada. Saldo insuficiente. Não se esqueça da taxa de transferência");
        }
    }




}

