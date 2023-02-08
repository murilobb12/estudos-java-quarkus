package br.com.murilo.model;

public enum TipoEnum {

    COMPRA("COMPRA"),
    VENDA("VENDA");

    private String descricao;

    TipoEnum(String descricao) {
        this.descricao = descricao;
    }


    public String getDescricao() {
        return descricao;
    }
}
