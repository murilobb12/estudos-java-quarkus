package br.com.letscode;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class ClientePJ extends Cliente{

    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public ClientePJ(String id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }


    public String formataCnpj(String cnpj) {
        String cnpjFormatado = "";
        try {
            final MaskFormatter formatador = new MaskFormatter("##.###.###/####-##");
            formatador.setValidCharacters(cnpj);
            formatador.setValueContainsLiteralCharacters(false);
            cnpjFormatado = formatador.valueToString(cnpj);
            return cnpjFormatado;
        } catch (ParseException e) {
            throw new RuntimeException("Valor gerado não bate com o padrão: " + cnpjFormatado, e);
        }
    }

    @Override
    public String toString() {
        String cnpjFormatado = formataCnpj(cnpj);
        return "Dados do usuário: \n"+ "Id: " + id + " - nome: " + nome + ", cnpj: " + cnpjFormatado;
    }
}

