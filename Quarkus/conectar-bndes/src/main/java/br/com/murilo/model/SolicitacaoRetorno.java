package br.com.murilo.model;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Array;
import java.time.LocalDate;

@ApplicationScoped
public class SolicitacaoRetorno {

    public LocalDate dataSolicitacao;

    public String[] erros;

    public int idSolicitacao;

    public Status status;

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String[] getErros() {
        return erros;
    }

    public void setErros(String[] erros) {
        this.erros = erros;
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
