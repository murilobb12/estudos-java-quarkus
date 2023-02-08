package org.example;

import java.math.BigDecimal;

public class ReajusteService {


    public BigDecimal reajusteSalarial(Funcionario funcionario) {

        BigDecimal reajuste = funcionario.getDesempenho().percentualReajuste();

        return funcionario.getSalario().multiply(reajuste);

    }

}
