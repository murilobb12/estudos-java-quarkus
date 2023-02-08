package DevDojo.Explicacao.OrientacaoObjetos.Classes.Teste;

import DevDojo.Explicacao.OrientacaoObjetos.Classes.Dominio.Estudante;
import DevDojo.Explicacao.OrientacaoObjetos.Classes.Dominio.Impressora;


public class EstudanteApp01 {

    public static void main(String[] args) {


        Impressora impressora = new Impressora();
        Estudante estudante = new Estudante();
        estudante.nome = "Murilo";
        estudante.idade = 27;
        estudante.sexo = "MASCULINO";

        impressora.imprimeEstudante(estudante);








    }

}
