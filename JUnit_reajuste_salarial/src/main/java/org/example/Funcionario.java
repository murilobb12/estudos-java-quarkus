package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Funcionario {

    private String nome;

    private BigDecimal salario;

    private Desempenho desempenho;

    public Funcionario(String nome, BigDecimal salario, Desempenho desempenho) {
        this.nome = nome;
        this.salario = salario;
        this.desempenho = desempenho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Desempenho getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(Desempenho desempenho) {
        this.desempenho = desempenho;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", salario=" + salario.setScale(2, RoundingMode.DOWN) +
                ", desempenho=" + desempenho +
                '}';
    }
}
