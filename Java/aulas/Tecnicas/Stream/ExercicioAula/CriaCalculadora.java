package aulas.Tecnicas.Stream.ExercicioAula;

import java.math.BigDecimal;

public class CriaCalculadora {

    public static void main(String[] args) {

        new CriaCalculadora().add(10).sub(2).divided(2).result().mostraConta();


    }


    String conta = "0";
    BigDecimal valor = BigDecimal.ZERO;

    public CriaCalculadora add(int valor){
        this.valor = this.valor.add(new BigDecimal(valor));
        this.conta += "+" + valor ;
        return this;
    }

    public CriaCalculadora sub(int valor){
        this.valor = this.valor.subtract(new BigDecimal(valor));
        this.conta += "-" + valor ;
        return this;
    }

    public CriaCalculadora divided(int valor){
        this.valor = this.valor.divide(new BigDecimal(valor));
        this.conta += "/" + valor ;
        return this;
    }

    public CriaCalculadora result(){
        this.conta += "=" + this.valor;
        return this;
    }

    public void mostraConta(){
        System.out.println(this.conta);
    }



}
