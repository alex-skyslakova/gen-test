import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CheckMachinFormulaTest {

    @Test
    public void testCorrectFormulas() {
        List<String> correctFormulas = new ArrayList<>();
        correctFormulas.add("arctan(1/2) + arctan(1/3)");
        correctFormulas.add("2*arctan(1/3) + arctan(1/7)");
        correctFormulas.add("4*arctan(1/5) - arctan(1/239)");
        correctFormulas.add("5*arctan(1/7) + 2*arctan(3/79)");
        correctFormulas.add("5*arctan(29/278) + 7*arctan(3/79)");
        correctFormulas.add("arctan(1/2) + arctan(1/5) + arctan(1/8)");
        correctFormulas.add("4*arctan(1/5) - arctan(1/70) + arctan(1/99)");
        correctFormulas.add("5*arctan(1/7) + 4*arctan(1/53) + 2*arctan(1/4443)");
        correctFormulas.add("6*arctan(1/8) + 2*arctan(1/57) + arctan(1/239)");
        correctFormulas.add("8*arctan(1/10) - arctan(1/239) - 4*arctan(1/515)");
        correctFormulas.add("12*arctan(1/18) + 8*arctan(1/57) - 5*arctan(1/239)");
        correctFormulas.add("16*arctan(1/21) + 3*arctan(1/239) + 4*arctan(3/1042)");
        correctFormulas.add("22*arctan(1/28) + 2*arctan(1/443) - 5*arctan(1/1393) - 10*arctan(1/11018)");
        correctFormulas.add("22*arctan(1/38) + 17*arctan(7/601) + 10*arctan(7/8149)");
        correctFormulas.add("44*arctan(1/57) + 7*arctan(1/239) - 12*arctan(1/682) + 24*arctan(1/12943)");
        correctFormulas.add("88*arctan(1/172) + 51*arctan(1/239) + 32*arctan(1/682) + 44*arctan(1/5357) + 68*arctan(1/12943)");

        for (String formula : correctFormulas) {
            Fraction result = CheckMachinFormula.tanRight(formula);
            assertEquals(new BigInteger("1"), result.numerator);
            assertEquals(new BigInteger("1"), result.denominator);
        }
    }

    @Test
    public void testIncorrectFormula() {
        String incorrectFormula = "88*arctan(1/172) + 51*arctan(1/239) + 32*arctan(1/682) + 44*arctan(1/5357) + 68*arctan(1/12944)";
        Fraction result = CheckMachinFormula.tanRight(incorrectFormula);
        assertNotEquals(new BigInteger("1"), result.numerator);
        assertNotEquals(new BigInteger("1"), result.denominator);
    }
}
