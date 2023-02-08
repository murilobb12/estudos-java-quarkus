package aulas.Tecnicas.Stream.ExercicioAula;

public class CriaSql {

    public static void main(String[] args) {
        new CriaSql().select("*").from("tabelaCliente").where("cliente.Name = Thiago").and("cliente.carro=ferrari").or("cliente.idade>40").endQuery().mostraQuery();
    }

    String query = "";

    public CriaSql select(String colunas){
        this.query += "SELECT " + colunas + " ";
        return this;
    }

    public CriaSql from(String tabela){
        this.query += "FROM " + tabela;
        return this;
    }

    public CriaSql where(String condicao){
        this.query += " WHERE " + condicao;
        return this;
    }

    public CriaSql and(String condicao){
        this.query += " AND " + condicao;
        return this;
    }

    public CriaSql or(String condicao){
        this.query += " OR " + condicao;
        return this;
    }

    public CriaSql endQuery(){
        this.query += ";";
        return this;
    }

    public void mostraQuery(){
        System.out.println(this.query);
    }





}
