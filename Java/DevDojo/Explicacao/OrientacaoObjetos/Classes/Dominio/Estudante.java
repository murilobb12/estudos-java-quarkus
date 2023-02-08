package DevDojo.Explicacao.OrientacaoObjetos.Classes.Dominio;

public class Estudante {

    public String nome;
    public int idade;
    public String sexo;


    @Override
    public String toString() {
        return this.nome+"\n" + this.sexo + this.idade;
    }
}

