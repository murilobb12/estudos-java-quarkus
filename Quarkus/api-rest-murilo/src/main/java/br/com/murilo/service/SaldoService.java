package br.com.murilo.service;

import br.com.murilo.dto.ContaDto;
import br.com.murilo.model.Conta;

import javax.enterprise.context.ApplicationScoped;
import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.security.InvalidParameterException;

@ApplicationScoped
public class SaldoService {

    public void sacar(Conta conta, BigDecimal valor){

        BigDecimal verificaSaldo = conta.getSaldo();

        if (valor.compareTo(verificaSaldo) > 0){
            throw new InvalidParameterException("Valor de saque maior que o saldo da conta.");
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));



    }

    public void depositar(Conta conta, BigDecimal valor){

        BigDecimal verificaSaldo = conta.getSaldo();

        if(valor.compareTo(BigDecimal.ZERO) == 0 || 0 > valor.compareTo(BigDecimal.ZERO)) {
            throw new InvalidParameterException("Valor de depósito tem que ser maior que zero");
        }

        conta.setSaldo(conta.getSaldo().add(valor));
    }


    public void rendimento(Conta conta, BigDecimal valor){

        conta.setSaldo(conta.getSaldo().multiply(random(2)));


    }

    public static BigDecimal random(int range) {
        BigDecimal max = new BigDecimal(range);
        BigDecimal randFromDouble = new BigDecimal(Math.random());
        BigDecimal actualRandomDec = randFromDouble.multiply(max);
        actualRandomDec = actualRandomDec
                .setScale(2, BigDecimal.ROUND_DOWN);
        return actualRandomDec;
    }




}
