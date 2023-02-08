package br.com.letscode;


import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {

        System.out.println("Criando cliente PF");
        ClientePF murilo = new ClientePF("Murilo", "Murilo", "38230414874");
        //Imprimindo os dados do cliente criado
        System.out.println(murilo.toString());
        System.out.println();

        System.out.println("Abrindo conta PF");
        Conta contaMurilo = new ContaCorrentePF( murilo,123);
        //Mostrando os dados da conta criada e realizando algumas operações.
        System.out.println(contaMurilo.toString());
        System.out.println();

        //Depositando e sacando valores superiores e inferiores ao saldo.
        contaMurilo.depositar(new BigDecimal("500.00"));
        contaMurilo.sacar(new BigDecimal("610.00"));
        contaMurilo.sacar(new BigDecimal("100.00"));
        contaMurilo.verSaldo();
        System.out.println();

        System.out.println("Abrindo conta investimento PF");
        //Abrindo uma conta Investimento PF
        ContaInvestimentoPF contaInvestimentoMurilo = new ContaInvestimentoPF( murilo, 12341234);
        contaMurilo.investir(new BigDecimal("100.00"), contaInvestimentoMurilo);

        //Rendendo o saldo da conta invstimento
        contaInvestimentoMurilo.render();
        contaMurilo.verSaldo();
        contaInvestimentoMurilo.verSaldo();
        System.out.println();

        System.out.println("Abrindo conta poupança PF");
        //Abrindo uma conta Poupança PF
        ContaPoupanca poupancaMurilo = new ContaPoupanca(murilo, 123123);
        poupancaMurilo.depositar(new BigDecimal("500.00"));
        poupancaMurilo.render();
        System.out.println(poupancaMurilo.toString());
        System.out.println();

        //Resgatando o valor da conta PF Investimento criada
        contaInvestimentoMurilo.resgatar(new BigDecimal("110.00"), contaMurilo);
        contaMurilo.verSaldo();
        System.out.println();

        System.out.println("Criando cliente PJ");
        //Criando um cliente PJ
        ClientePJ tiago = new ClientePJ("Tiago", "Tiago","40008098000148");

        //Imprimindo os dados do cliente criado
        System.out.println(tiago.toString());
        System.out.println();

        System.out.println("Abrindo conta corrente PJ");
        //Abrindo a conta corrente PJ do Tiago
        ContaCorrentePJ contaTiago = new ContaCorrentePJ(tiago, 999);

        //Mostrando os dados da conta criada e realizando algumas operações.
        System.out.println(contaTiago.toString());
        System.out.println();

        //Depositando e sacando valores superiores e inferiores ao saldo.
        contaTiago.depositar(new BigDecimal("1000.00"));
        System.out.println();
        contaTiago.sacar(new BigDecimal("1000.00"));
        contaTiago.sacar(new BigDecimal("800.00"));
        contaTiago.verSaldo();
        System.out.println();

        System.out.println("Abrindo conta investimentos PJ");
        //Abrindo conta Investimentos PJ do Tiago
        ContaInvestimentoPJ contaInvestimentoTiago = new ContaInvestimentoPJ(tiago, 999999);
        contaTiago.investir(new BigDecimal("100.00"), contaInvestimentoTiago);

        //Rendendo o dinheiro da conta Investimento
        contaInvestimentoTiago.verSaldo();
        contaInvestimentoTiago.render();
        contaInvestimentoTiago.verSaldo();
        System.out.println();

        //Resgatando o dinheiro da conta Investimento
        contaInvestimentoTiago.resgatar(new BigDecimal("100.00"), contaTiago);
        System.out.println();


        System.out.println("Realizando transferência de valores");
        //Transferindo da conta Murilo PF para Tiago PJ
        contaMurilo.verSaldo();
        contaTiago.verSaldo();
        contaMurilo.tranferir(new BigDecimal("100.00"),contaTiago);
        contaMurilo.verSaldo();
        contaTiago.verSaldo();


    }

}

