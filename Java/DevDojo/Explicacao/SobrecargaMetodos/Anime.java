package DevDojo.Explicacao.SobrecargaMetodos;

public class Anime {
    private String nome;
    private String tipo;
    private int episodios;


    public void init(String nome, String tipo, int episodios){
        this.nome = nome;
    this.tipo = tipo;
        this.episodios = episodios;

    }

public String imprime(){
        return "Tipo: " +this.tipo + "\nEpisodios: " + this.episodios + "\nNome: " + this.nome ;
    }

public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setEpisodios(int episodios){
        this.episodios = episodios;
    }

public String getTipo(){
        return this.tipo;
    }

    public int getEpisodios(){
        return this.episodios;
    }

}
