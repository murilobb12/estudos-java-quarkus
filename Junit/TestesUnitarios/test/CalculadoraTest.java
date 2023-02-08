import Classes.Calculadora;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    @Test
    public void deveriaSomarDoisNumerosPositivos(){
        Calculadora calculadora = new Calculadora();
        int soma = calculadora.soma(1,2);

    }

}
