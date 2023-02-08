package aulas.Julho._15;

import java.io.Serializable;
import java.security.InvalidParameterException;

public class Pessoa implements Cloneable, Serializable, Comparable<Pessoa> {

    private String nome;
    private String apelido;
    private int idade;
    private int cpf;

    public Pessoa(final String _nome, final String _apelido, final int _idade) {
        this.nome = _nome;
        this.apelido = _apelido;
        this.idade = _idade;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getCpf() {
        return cpf;
    }

    protected void criarCartão(int _cpf) {
        if (this.cpf != 0) {
            throw new InvalidParameterException();
        }
        this.cpf = _cpf;
    }

    @Override
    public Pessoa clone() throws CloneNotSupportedException {
        return (Pessoa) super.clone();
    }

    public Pessoa criarClone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override
    public String toString() {
        return "Eu chamo-me " + this.nome + " " + this.apelido + " e tenho " + this.idade + " anos."
                + (this.cpf != 0 ? " O meu CPF é: " + this.cpf : "");
    }

    @Override
    public int compareTo(Pessoa o) {
//        Comparar através da idade
//        return this.idade - o.idade;

        return this.nome.compareTo(o.nome);


    }
}
