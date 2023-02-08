package aulas.Julho._18.Generics.Exercicio;

public class App {

    public static void main(String[] args) {

        Pessoa pessoa1 = new Pessoa("Maria", 45);
        Pessoa pessoa2 = new Pessoa("Joao",55);

        Par<Pessoa, Carro> par1 = compraCarro(pessoa1);
        Par<Pessoa, Carro> par2 = compraCarro(pessoa2);


        System.out.println(par1.getPrimeiro().getNome() + " comprou um carro "+ par1.getSegundo().getCor());
        System.out.println(par2.getPrimeiro().getNome() + " comprou um carro "+ par2.getSegundo().getCor());    
        
        
    }

    public static Par<Pessoa, Carro> compraCarro (Pessoa pessoa){
        if (pessoa.getIdade() > 50 ) {
            return new Par<Pessoa,Carro>(pessoa, new Carro("branco"));
        } 
        return new Par<Pessoa,Carro>(pessoa, new Carro("preto"));
    }    
    
}
