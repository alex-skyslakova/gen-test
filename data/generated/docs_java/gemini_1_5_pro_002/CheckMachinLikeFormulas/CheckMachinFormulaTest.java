import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CheckMachinFormulaTest {

    @Test
    void testTanLeft() {
        assertEquals("1", CheckMachinFormula.tanLeft("pi/4"));
        assertThrows(RuntimeException.class, () -> CheckMachinFormula.tanLeft("invalid"));
    }

    @Test
    void testTanRight() {
        assertEquals(new CheckMachinFormula.Fraction("1","1"), CheckMachinFormula.tanRight("arctan(1/2) + arctan(1/3)"));
        assertEquals(new CheckMachinFormula.Fraction("1","1"), CheckMachinFormula.tanRight("2*arctan(1/3) + arctan(1/7)"));
        assertEquals(new CheckMachinFormula.Fraction("1","1"), CheckMachinFormula.tanRight("4*arctan(1/5) - arctan(1/239)"));
        assertEquals(new CheckMachinFormula.Fraction("1","1"), CheckMachinFormula.tanRight("5*arctan(1/7) + 2*arctan(3/79)"));
        assertEquals(new CheckMachinFormula.Fraction("1", "1"), CheckMachinFormula.tanRight("5*arctan(29/278) + 7*arctan(3/79)"));
        assertEquals(new CheckMachinFormula.Fraction("1", "1"), CheckMachinFormula.tanRight("arctan(1/2) + arctan(1/5) + arctan(1/8)"));
        assertEquals(new CheckMachinFormula.Fraction("1", "1"), CheckMachinFormula.tanRight("4*arctan(1/5) - arctan(1/70) + arctan(1/99)"));
        // ... (Add tests for all correct formulas)


        // Incorrect Formula
        assertNotEquals(new CheckMachinFormula.Fraction("1","1"), CheckMachinFormula.tanRight("88*arctan(1/172) + 51*arctan(1/239) + 32*arctan(1/682) + 44*arctan(1/5357) + 68*arctan(1/12944)"));

    }

    @Test
    void testEvaluateArctan_singleTerm() {
        List<CheckMachinFormula.Term> terms = new ArrayList<>();
        terms.add(new CheckMachinFormula.Term(1, new CheckMachinFormula.Fraction("1", "2")));
        assertEquals(new CheckMachinFormula.Fraction("1", "2"), CheckMachinFormula.evaluateArctan(terms));


        terms.clear();
        terms.add(new CheckMachinFormula.Term(-1, new CheckMachinFormula.Fraction("1", "2")));
        assertEquals(new CheckMachinFormula.Fraction("-1", "2"), CheckMachinFormula.evaluateArctan(terms));
    }


    @Test
    void testEvaluateArctan_multipleTerms(){
        List<CheckMachinFormula.Term> terms = new ArrayList<>();
        terms.add(new CheckMachinFormula.Term(1, new CheckMachinFormula.Fraction("1", "2")));
        terms.add(new CheckMachinFormula.Term(1, new CheckMachinFormula.Fraction("1", "3")));
        assertEquals(new CheckMachinFormula.Fraction("1", "1"), CheckMachinFormula.evaluateArctan(terms));


    }

    @Test
    void testEvaluateArctan_coefficient() {
        assertEquals(new CheckMachinFormula.Fraction("1", "2"), CheckMachinFormula.evaluateArctan(1, new CheckMachinFormula.Fraction("1", "2")));
        assertEquals(new CheckMachinFormula.Fraction("-1", "2"), CheckMachinFormula.evaluateArctan(-1, new CheckMachinFormula.Fraction("1", "2")));
        //add test cases for coefficients 2,3,4 etc
    }

    @Test
    void testArctanFormula() {
        CheckMachinFormula.Fraction f1 = new CheckMachinFormula.Fraction("1", "2");
        CheckMachinFormula.Fraction f2 = new CheckMachinFormula.Fraction("1", "3");
        assertEquals(new CheckMachinFormula.Fraction("1", "1"), CheckMachinFormula.arctanFormula(f1, f2));
    }

    // Fraction tests 
     @Test
    void testFraction_add() {
        CheckMachinFormula.Fraction f1 = new CheckMachinFormula.Fraction("1", "2");
        CheckMachinFormula.Fraction f2 = new CheckMachinFormula.Fraction("1", "3");
        assertEquals(new CheckMachinFormula.Fraction("5", "6"), f1.add(f2));

    }


    @Test
    void testFraction_subtract() {
       CheckMachinFormula.Fraction f1 = new CheckMachinFormula.Fraction("1", "2");
        CheckMachinFormula.Fraction f2 = new CheckMachinFormula.Fraction("1", "3");
        assertEquals(new CheckMachinFormula.Fraction("1", "6"), f1.subtract(f2));
    }

    @Test
    void testFraction_multiply() {
        CheckMachinFormula.Fraction f1 = new CheckMachinFormula.Fraction("1", "2");
        CheckMachinFormula.Fraction f2 = new CheckMachinFormula.Fraction("1", "3");
        assertEquals(new CheckMachinFormula.Fraction("1", "6"), f1.multiply(f2));
    }


     @Test
    void testFraction_divide() {
       CheckMachinFormula.Fraction f1 = new CheckMachinFormula.Fraction("1", "2");
        CheckMachinFormula.Fraction f2 = new CheckMachinFormula.Fraction("1", "3");
        assertEquals(new CheckMachinFormula.Fraction("3", "2"), f1.divide(f2));
    }


    @Test
    void testFraction_negate() {
        CheckMachinFormula.Fraction f1 = new CheckMachinFormula.Fraction("1", "2");
        assertEquals(new CheckMachinFormula.Fraction("-1", "2"), f1.negate());

    }



}
