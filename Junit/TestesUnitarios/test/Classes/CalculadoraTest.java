package Classes;


import org.junit.jupiter.*;
import org.junit.jupiter.api.Test;


class CalculadoraTest {

    @Test
    void testSoma() {
        Calculadora calculadora = new Calculadora();
        assertEquals("1 + 2 deve ser 3.", 3, calculadora.soma(1,2));
    }
}