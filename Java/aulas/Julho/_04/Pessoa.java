package aulas.Julho._04;

import java.math.BigDecimal;

public class Pessoa {
    private String nome;
    private int idade;
    private Sexo sexo;
    private String endereco;
    private EstadoCivil estadoCivil;
    private BigDecimal altura;
    private BigDecimal peso;

    //Criação do construtor principal contendo todos os atributos da classe
    public Pessoa(String nome, int idade, Sexo sexo, String endereco, EstadoCivil estadoCivil, BigDecimal altura, BigDecimal peso) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.endereco = endereco;
        this.estadoCivil = estadoCivil;
        this.altura = altura;
        this.peso = peso;
    }

    //Construtor sendo necessário passar apenas nome, idade e sexo;
    public Pessoa(String nome, int idade, Sexo sexo) {
        this(nome, idade, sexo, null, null, null, null);
    }

    //Construtor sendo necessário passar apenas nome e endereço; Se atentar as posiçoes corretas do this.
    public Pessoa(String nome, String endereco) {
        this(nome, 0, null, endereco, null, null, null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public enum Sexo {
        MASCULINO("Masculino"), FEMININO("Feminino"), NAO_DEFINIDO("Não definido");

        private String descricao;

        Sexo(String _descricao) {
            this.descricao = _descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public enum EstadoCivil {
        SOLTEIRO("Solteiro(a)"), CASADO("Casado(a)"), VIUVO("Viuvo(a)"), DIVORCIADO("Divorciado(a)");

        private String descricao;

        EstadoCivil(String _descricao) {
            this.descricao = _descricao;
        }

        public String getDescricao() {
            return this.descricao;
        }
    }
}
