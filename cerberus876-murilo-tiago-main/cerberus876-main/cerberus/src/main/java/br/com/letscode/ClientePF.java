package br.com.letscode;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class ClientePF extends Cliente{

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ClientePF(String id, String nome, String cpf) {
        super(id, nome);
        this.cpf = cpf;
    }


    public String formataCpf(String strCpf) {
        String cpfFormatado = "";
        try {
            final MaskFormatter formatador = new MaskFormatter("###.###.###-##");
            formatador.setValidCharacters(strCpf);
            formatador.setValueContainsLiteralCharacters(false);
            cpfFormatado = formatador.valueToString(strCpf);
            return cpfFormatado;
        } catch (ParseException e) {
            throw new RuntimeException("Valor gerado não bate com o padrão: " + cpfFormatado, e);
        }
    }

    @Override
    public String toString() {
        String cpfFormatado = formataCpf(cpf);
        return "Dados do usuário: \n"+ "Id: " + id + " - nome: " + nome + ", cpf: " + cpfFormatado;
    }

}
