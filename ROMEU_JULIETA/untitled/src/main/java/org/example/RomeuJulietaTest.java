package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RomeuJulietaTest {


    RomeuJulieta romeuJulieta = new RomeuJulieta();


    @Test
    public void pasando3RetornaQueijoTeste() {
        String retorno = romeuJulieta.romeu_julieta(3);

        Assert.assertEquals("Queijo", retorno);
    }

    @Test
    public void passando5RetornaGoiabadaTest() {
        String retorno = romeuJulieta.romeu_julieta(5);

        Assert.assertEquals("Goiabada", retorno);
    }

    @Test
    public void passando30RetornaRomeuJulietaTest() {
        String retorno = romeuJulieta.romeu_julieta(30);

        Assert.assertEquals("Romeu e Julieta", retorno);
    }

    @Test
    public void passando1Retorna1Test() {
        String retorno = romeuJulieta.romeu_julieta(1);

        Assert.assertEquals("1", retorno);
    }

    @Test
    public void passando13Retorna13Test() {
        String retorno = romeuJulieta.romeu_julieta(13);

        Assert.assertEquals("13", retorno);
    }

    @Test
    public void passando0RetornaErro() {
        Assert.assertThrows(Exception.class, () -> {
            romeuJulieta.romeu_julieta(0);
        });
    }

    @Test
    public void passandoStringRetornaErro() {
        Assert.assertThrows(Exception.class, () -> {
            romeuJulieta.romeu_julieta(Integer.parseInt("Teste"));
        });
    }


}
