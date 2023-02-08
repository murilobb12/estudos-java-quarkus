import java.math.BigDecimal;
import java.math.RoundingMode;

public class Teste{


public static void main(String[] args) {
    



System.out.println(random(5));








}

public static BigDecimal random(int range) {
    BigDecimal max = new BigDecimal(range);
    BigDecimal randFromDouble = new BigDecimal(Math.random());
    BigDecimal actualRandomDec = randFromDouble.multiply(max);
    actualRandomDec = actualRandomDec
            .setScale(2, RoundingMode.DOWN);
    return actualRandomDec;
}
}