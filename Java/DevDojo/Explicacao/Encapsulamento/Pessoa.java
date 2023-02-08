package DevDojo.Explicacao.Encapsulamento;

public class Pessoa {

    public String nome;
    public int idade;

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public int getIdade(){
        return this.idade;
    }

}
