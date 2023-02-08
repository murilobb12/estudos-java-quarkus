package br.com.murilo.model.bndes;

import lombok.Data;

@Data
public class OcorrenciaValidacaoDTO {

    public String idObjetoValidado;

    public String mensagem;

    public String siglaSeveridade;

}
