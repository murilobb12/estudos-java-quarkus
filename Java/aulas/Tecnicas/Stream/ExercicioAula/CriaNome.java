package aulas.Tecnicas.Stream.ExercicioAula;

public class CriaNome {

    public static void main(String[] args) {
        new CriaNome().name("Murilo").sobrenome("Alberto").apelido("dos Santos").imprimeNome();
    }

    String name;
    String sobrenome;
    String apelido;

    public CriaNome name(String nome){
        this.name = nome;
        return this;
    }

    public CriaNome sobrenome(String sobrenome){
        this.sobrenome = sobrenome;
        return this;
    }

    public CriaNome apelido(String apelido){
        this.apelido = apelido;
        return this;
    }

    public void imprimeNome(){
        System.out.println(this.name + " " + this.sobrenome + " " + this.apelido + " ");
    }

}
