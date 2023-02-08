package br.com.murilo.dto;

import javax.persistence.Column;
import java.math.BigDecimal;

public class ContaDto {

    @Column(name = "numero_conta")
    private int numeroConta;

    private BigDecimal saldo = BigDecimal.ZERO;

    @Column(name = "cliente_id")
    private Long clienteId;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
