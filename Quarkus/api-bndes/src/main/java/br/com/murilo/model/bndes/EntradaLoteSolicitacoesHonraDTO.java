package br.com.murilo.model.bndes;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Data
public class EntradaLoteSolicitacoesHonraDTO {

    public SolicitacaoHonraEnvioDTO[] solicitacoesHonra;

}
