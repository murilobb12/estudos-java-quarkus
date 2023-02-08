package br.com.murilo.model.bndes;

import java.math.BigDecimal;

/**
 * Classe responsável por mapear a resposta da consulta de operação pelo ID AGENTE FINANCEIRO
 * endpoint: honra/solicitacoes/operacao/credito-livre/{idAgenteFinanceiro}
 *
 */
public class SolicitacaoHonraConsultaDTO {

        public int idSolicitacaoHonra;

        public String idOperacaoAgenteCreditoLivre;

        public String sistemaRepasse;

        public String contratoRepasse;

        public String dataParcelaInicialASerHonrada;

        public String dataEstimativaHonra;

        public BigDecimal valorEstimadoHonra;

        public String comLimite;

        public EstadoDTO estadoDTO;

}
