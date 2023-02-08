import org.example.Desempenho;
import org.example.Funcionario;
import org.example.ReajusteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReajusteSalarialTest {


    private ReajusteService reajusteService;

    @BeforeEach
    public void inicializar(){
        this.reajusteService = new ReajusteService();
    }

    @Test
    public void reajusteDeveriaSerTresPorCentoParaDesempenhoA() {

        Funcionario funcionario = new Funcionario("Murilo", new BigDecimal("1000.00"), Desempenho.A);
        assertEquals(new BigDecimal("1030.00"), reajusteService.reajusteSalarial(funcionario).setScale(2, RoundingMode.DOWN));

    }

    @Test
    public void reajusteDeveriaSerQuinzePorCentoParaDesempenhoB() {

        Funcionario funcionario = new Funcionario("Murilo", new BigDecimal("1000.00"), Desempenho.B);
        assertEquals(new BigDecimal("1150.00"), reajusteService.reajusteSalarial(funcionario).setScale(2, RoundingMode.DOWN));

    }

    @Test
    public void reajusteDeveriaSerVintePorCentoParaDesempenhoB() {


        Funcionario funcionario = new Funcionario("Murilo", new BigDecimal("1000.00"), Desempenho.C);
        assertEquals(new BigDecimal("1200.00"), reajusteService.reajusteSalarial(funcionario).setScale(2, RoundingMode.DOWN));

    }

}
