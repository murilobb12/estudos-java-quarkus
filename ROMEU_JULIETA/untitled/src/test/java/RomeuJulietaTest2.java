import org.example.Main;
import org.example.RomeuJulieta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RomeuJulietaTest2 {

    RomeuJulieta romeuJulieta = new RomeuJulieta();


    @Test
    public void pasando3RetornaQueijoTeste() {
        String retorno = romeuJulieta.romeu_julieta(3);

        Assertions.assertEquals("Queijo", retorno);
    }

    @Test
    public void passando5RetornaGoiabadaTest() {
        String retorno = romeuJulieta.romeu_julieta(5);

        Assertions.assertEquals("Goiabada", retorno);
    }

    @Test
    public void passando30RetornaRomeuJulietaTest() {
        String retorno = romeuJulieta.romeu_julieta(30);

        Assertions.assertEquals("Romeu e Julieta", retorno);
    }

    @Test
    public void passando1Retorna1Test() {
        String retorno = romeuJulieta.romeu_julieta(1);

        Assertions.assertEquals("1", retorno);
    }

    @Test
    public void passando13Retorna13Test() {
        String retorno = romeuJulieta.romeu_julieta(13);

        Assertions.assertEquals("13", retorno);
    }

    @Test
    public void passando0RetornaErro() {
        Assertions.assertThrows(Exception.class, () -> {
            romeuJulieta.romeu_julieta(0);
        });
    }

    @Test
    public void passandoStringRetornaErro() {
        Assertions.assertThrows(Exception.class, () -> {
            romeuJulieta.romeu_julieta(Integer.parseInt("Teste"));
        });
    }


}
