package java_.EstudosMurilo.Colecoes.Contas;

import java.math.BigDecimal;
import java.util.Objects;

public class ContaCorrente implements Comparable<ContaCorrente>{

    public Integer numero;

    public int agencia;

    public String titular;

    public BigDecimal saldo = BigDecimal.ZERO;


    public BigDecimal sacar(BigDecimal valorSaque){
        return this.saldo.subtract(valorSaque);
    }

    public void depositar(BigDecimal valorDeposito){
        this.saldo = this.saldo.add(valorDeposito);
    }

    public ContaCorrente(Integer numero, int agencia, String titular) {
        this.numero = numero;
        this.agencia = agencia;
        this.titular = titular;
    }

    public ContaCorrente(Integer numero, int agencia) {
        this.numero = numero;
        this.agencia = agencia;
    }

    public ContaCorrente() {
        }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "numero=" + numero +
                ", agencia=" + agencia +
                ", saldo=" + saldo +
                ", titular=" + titular +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaCorrente that = (ContaCorrente) o;
        return agencia == that.agencia && Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, agencia);
    }

    @Override
    public int compareTo(ContaCorrente o) {
        return this.saldo.compareTo(o.saldo);
    }
}
