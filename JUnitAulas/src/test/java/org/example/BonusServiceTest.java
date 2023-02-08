package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest {

    @Test
    public void bonusZeradoParaFuncionariosComSalarioAlto(){

        Funcionario funcionario = new Funcionario("Murilo", LocalDate.now(), new BigDecimal("10000.01"));
        BonusService bonusService = new BonusService();
        BigDecimal bonus = bonusService.calcularBonus(funcionario);

        assertEquals(BigDecimal.ZERO, bonus);

    }

    @Test
    public void bonusDezPorCentoParaSalarioMenorQueDezMil(){

        Funcionario funcionario = new Funcionario("Murilo", LocalDate.now(), new BigDecimal("9999.99"));
        BonusService bonusService = new BonusService();
        BigDecimal bonus = bonusService.calcularBonus(funcionario);

        assertEquals(new BigDecimal("999.99"), bonus.setScale(2, RoundingMode.DOWN));

    }

    @Test
    public void valorIgualAoSalarioTeto(){
        Funcionario funcionario = new Funcionario("Murilo", LocalDate.now(), new BigDecimal("10000.00"));
        BonusService bonusService = new BonusService();
        BigDecimal bonus = bonusService.calcularBonus(funcionario);

        assertEquals(new BigDecimal("1000.00"), bonus.setScale(2,RoundingMode.DOWN));


    }


}