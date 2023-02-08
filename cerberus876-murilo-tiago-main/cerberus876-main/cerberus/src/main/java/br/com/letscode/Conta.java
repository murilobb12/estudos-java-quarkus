package br.com.letscode;

import java.math.BigDecimal;

//Crie uma classe Conta com os atributos numero da conta, titular e saldo e com os métodos sacar e depositar.
//A única forma de alterar o saldo fora da classe deve ser utilizando os métodos.
public abstract class Conta {

    protected Cliente titular;
    protected int numeroConta;
    protected BigDecimal saldo = new BigDecimal("0.00");

    public int getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }
    public void setTitular(Cliente titular) {
        this.titular = titular;
    }
    public Conta(Cliente titular, int numeroConta) {
        super();
        this.titular = titular;
        this.numeroConta = numeroConta;
//        this.saldo = saldo;

    }

    public void sacar(BigDecimal saque) {
        if(saque.compareTo(saldo)!=1) {
            this.saldo = saldo.subtract(saque);
            System.out.println("Foi realizado com sucesso o saque de R$"+ saque);
        }else {
            System.out.println("Saldo insuficiente. Saldo: " + this.saldo + " Valor saque: " + saque);
        }
    }
    public void sacarTransferencia(BigDecimal saque) {
        if(saque.compareTo(saldo)!=1) {
            this.saldo = saldo.subtract(saque);
        }else {
            System.out.println("Saldo insuficiente");
        }
    }
    public void depositarTransferencia(BigDecimal deposito) {
        this.saldo = saldo.add(deposito);
    }

    public void depositar(BigDecimal deposito) {
        this.saldo = saldo.add(deposito);
        System.out.println("Foi realizado com sucesso o depósito de R$"+ deposito);
    }
    public void verSaldo() {
        System.out.printf("O saldo da conta do " + titular.getNome() +" é de R$%.2f \n", saldo);
    }

    public void tranferir(BigDecimal transferencia, Conta conta) {
        if(transferencia.compareTo(saldo)!=1) {
            System.out.printf("Transferência de R$%.2f da conta %d para a conta %d \n", transferencia, numeroConta, conta.numeroConta );
            sacarTransferencia(transferencia);
            conta.depositarTransferencia(transferencia);
        }else {
            System.out.println("Transferência negada. Saldo insuficiente.");
        }
    }

    public void investir(BigDecimal aplicacao, Conta conta) {

        if(aplicacao.compareTo(saldo)!=1) {
            System.out.printf("Aplicação de R$%.2f da conta %d para a conta investimento %d \n", aplicacao, numeroConta, conta.numeroConta );
            sacarTransferencia(aplicacao);
            conta.depositarTransferencia(aplicacao);
        }else {
            System.out.println("Aplicação negada. Saldo insuficiente.");
        }
    }

    @Override
    public String toString() {

        return "Dados da conta: \n"+"Conta " + numeroConta +"- titular: " + titular.getNome() + ". Possui o saldo: R$" + saldo;
    }
}

