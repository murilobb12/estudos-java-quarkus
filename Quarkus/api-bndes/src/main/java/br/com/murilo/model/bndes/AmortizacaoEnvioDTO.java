package br.com.murilo.model.bndes;

import lombok.Data;

@Data
public class AmortizacaoEnvioDTO {

    public String dataVencimento;

    public Integer id;

    public Double valorDevido;

    public Double valorRecebido;

    public Boolean valorRecebimentoInferiorAoDevido;

    public Integer versao;

}
