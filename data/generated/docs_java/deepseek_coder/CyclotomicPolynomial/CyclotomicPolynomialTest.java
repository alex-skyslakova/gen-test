import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

public class CyclotomicPolynomialTest {

    @BeforeAll
    public static void setup() {
        // Initialize any necessary setup here
    }

    @Test
    public void testCyclotomicPolynomialForSmallN() {
        // Test the first few cyclotomic polynomials
        assertEquals("x - 1", CyclotomicPolynomial.cyclotomicPolynomial(1).toString());
        assertEquals("x + 1", CyclotomicPolynomial.cyclotomicPolynomial(2).toString());
        assertEquals("x^2 + x + 1", CyclotomicPolynomial.cyclotomicPolynomial(3).toString());
        assertEquals("x^2 + 1", CyclotomicPolynomial.cyclotomicPolynomial(4).toString());
        assertEquals("x^4 + x^3 + x^2 + x + 1", CyclotomicPolynomial.cyclotomicPolynomial(5).toString());
        assertEquals("x^2 - x + 1", CyclotomicPolynomial.cyclotomicPolynomial(6).toString());
    }

    @Test
    public void testHasCoefficientAbs() {
        Polynomial poly = new Polynomial(1, 2, -1, 1, 2, 0); // x^2 - x + 2
        assertTrue(poly.hasCoefficientAbs(1));
        assertTrue(poly.hasCoefficientAbs(2));
        assertFalse(poly.hasCoefficientAbs(3));
    }

    @Test
    public void testGetDivisors() {
        List<Integer> divisors = CyclotomicPolynomial.getDivisors(12);
        assertEquals(6, divisors.size());
        assertTrue(divisors.contains(1));
        assertTrue(divisors.contains(2));
        assertTrue(divisors.contains(3));
        assertTrue(divisors.contains(4));
        assertTrue(divisors.contains(6));
        assertTrue(divisors.contains(12));
    }

    @Test
    public void testGetFactors() {
        Map<Integer, Integer> factors = CyclotomicPolynomial.getFactors(12);
        assertEquals(2, factors.size());
        assertEquals(2, (int) factors.get(2));
        assertEquals(1, (int) factors.get(3));
    }

    @Test
    public void testPolynomialAddition() {
        Polynomial poly1 = new Polynomial(1, 2, -1, 1); // x^2 - x
        Polynomial poly2 = new Polynomial(1, 1, 2, 0);  // x + 2
        Polynomial result = poly1.add(poly2);
        assertEquals("x^2 + 2", result.toString());
    }

    @Test
    public void testPolynomialMultiplication() {
        Polynomial poly1 = new Polynomial(1, 1); // x
        Polynomial poly2 = new Polynomial(1, 1); // x
        Polynomial result = poly1.multiply(new Term(1, 1));
        assertEquals("x^2", result.toString());
    }

    @Test
    public void testPolynomialDivision() {
        Polynomial poly1 = new Polynomial(1, 2, -1, 0); // x^2 - 1
        Polynomial poly2 = new Polynomial(1, 1, -1, 0); // x - 1
        Polynomial result = poly1.divide(poly2);
        assertEquals("x + 1", result.toString());
    }
}
