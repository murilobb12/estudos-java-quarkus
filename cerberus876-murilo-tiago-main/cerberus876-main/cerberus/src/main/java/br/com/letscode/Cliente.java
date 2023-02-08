package br.com.letscode;

public abstract class Cliente {
    protected String id;
    protected String nome;

    public Cliente(String id, String nome) {

        super();
        this.id = id;
        this.nome = nome;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String toString() {
        return "Dados do usuário: \n" + "Id: " + id + "- nome: " + nome;
    }
}

