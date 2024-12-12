import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckMachinFormulaTest {

    @Test
    public void testFormula1() {
        assertEquals(1, CheckMachinFormula.tanRight("arctan(1/2) + arctan(1/3)").toString());
    }

    @Test
    public void testFormula2() {
        assertEquals(1, CheckMachinFormula.tanRight("2*arctan(1/3) + arctan(1/7)").toString());
    }

    @Test
    public void testFormula3() {
        assertEquals(1, CheckMachinFormula.tanRight("4*arctan(1/5) - arctan(1/239)").toString());
    }

    @Test
    public void testFormula4() {
        assertEquals(1, CheckMachinFormula.tanRight("5*arctan(1/7) + 2*arctan(3/79)").toString());
    }

    @Test
    public void testFormula5() {
        assertEquals(1, CheckMachinFormula.tanRight("5*arctan(29/278) + 7*arctan(3/79)").toString());
    }

    @Test
    public void testFormula6() {
        assertEquals(1, CheckMachinFormula.tanRight("arctan(1/2) + arctan(1/5) + arctan(1/8)").toString());
    }

    @Test
    public void testFormula7() {
        assertEquals(1, CheckMachinFormula.tanRight("4*arctan(1/5) - arctan(1/70) + arctan(1/99)").toString());
    }

    @Test
    public void testFormula8() {
        assertEquals(1, CheckMachinFormula.tanRight("5*arctan(1/7) + 4*arctan(1/53) + 2*arctan(1/4443)").toString());
    }

    @Test
    public void testFormula9() {
        assertEquals(1, CheckMachinFormula.tanRight("6*arctan(1/8) + 2*arctan(1/57) + arctan(1/239)").toString());
    }

    @Test
    public void testFormula10() {
        assertEquals(1, CheckMachinFormula.tanRight("8*arctan(1/10) - arctan(1/239) - 4*arctan(1/515)").toString());
    }

    @Test
    public void testFormula11() {
        assertEquals(1, CheckMachinFormula.tanRight("12*arctan(1/18) + 8*arctan(1/57) - 5*arctan(1/239)").toString());
    }

    @Test
    public void testFormula12() {
        assertEquals(1, CheckMachinFormula.tanRight("16*arctan(1/21) + 3*arctan(1/239) + 4*arctan(3/1042)").toString());
    }

    @Test
    public void testFormula13() {
        assertEquals(1, CheckMachinFormula.tanRight("22*arctan(1/28) + 2*arctan(1/443) - 5*arctan(1/1393) - 10*arctan(1/11018)").toString());
    }

    @Test
    public void testFormula14() {
        assertEquals(1, CheckMachinFormula.tanRight("22*arctan(1/38) + 17*arctan(7/601) + 10*arctan(7/8149)").toString());
    }

    @Test
    public void testFormula15() {
        assertEquals(1, CheckMachinFormula.tanRight("44*arctan(1/57) + 7*arctan(1/239) - 12*arctan(1/682) + 24*arctan(1/12943)").toString());
    }

    @Test
    public void testFormula16() {
        assertEquals(1, CheckMachinFormula.tanRight("88*arctan(1/172) + 51*arctan(1/239) + 32*arctan(1/682) + 44*arctan(1/5357) + 68*arctan(1/12943)").toString());
    }

    @Test
    public void testIncorrectFormula() {
        assertEquals("Not 1", CheckMachinFormula.tanRight("88*arctan(1/172) + 51*arctan(1/239) + 32*arctan(1/682) + 44*arctan(1/5357) + 68*arctan(1/12944)").toString());
    }
}
