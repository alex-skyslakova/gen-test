import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PtTest {

    @Test
    public void testZeroPoint() {
        Pt zero = Pt.zero();
        assertTrue(zero.isZero());
        assertEquals("Zero", zero.toString());
    }

    @Test
    public void testFromY() {
        Pt point = Pt.fromY(1);
        assertEquals(0.0, point.x, 0.001);
        assertEquals(1.0, point.y, 0.001);
    }

    @Test
    public void testPointDoubling() {
        Pt point = Pt.fromY(1);
        Pt doubled = point.dbl();
        assertEquals(-1.125, doubled.x, 0.001);
        assertEquals(-0.203, doubled.y, 0.001);
    }

    @Test
    public void testPointNegation() {
        Pt point = Pt.fromY(1);
        Pt negated = point.neg();
        assertEquals(0.0, negated.x, 0.001);
        assertEquals(-1.0, negated.y, 0.001);
    }

    @Test
    public void testPointAddition() {
        Pt a = Pt.fromY(1);
        Pt b = Pt.fromY(2);
        Pt sum = a.plus(b);
        assertEquals(-1.686, sum.x, 0.001);
        assertEquals(-1.884, sum.y, 0.001);
    }

    @Test
    public void testPointAdditionWithZero() {
        Pt a = Pt.fromY(1);
        Pt zero = Pt.zero();
        Pt sum = a.plus(zero);
        assertEquals(a.x, sum.x, 0.001);
        assertEquals(a.y, sum.y, 0.001);
    }

    @Test
    public void testPointMultiplication() {
        Pt a = Pt.fromY(1);
        Pt result = a.mult(12345);
        assertEquals(-1.686, result.x, 0.001);
        assertEquals(-1.884, result.y, 0.001);
    }

    @Test
    public void testPointMultiplicationByZero() {
        Pt a = Pt.fromY(1);
        Pt result = a.mult(0);
        assertTrue(result.isZero());
    }

    @Test
    public void testPointMultiplicationByOne() {
        Pt a = Pt.fromY(1);
        Pt result = a.mult(1);
        assertEquals(a.x, result.x, 0.001);
        assertEquals(a.y, result.y, 0.001);
    }

    @Test
    public void testPointAdditionAndNegation() {
        Pt a = Pt.fromY(1);
        Pt b = Pt.fromY(2);
        Pt sum = a.plus(b);
        Pt negSum = sum.neg();
        Pt result = a.plus(b).plus(negSum);
        assertTrue(result.isZero());
    }

    @Test
    public void testPointAdditionAndDoubling() {
        Pt a = Pt.fromY(1);
        Pt b = Pt.fromY(2);
        Pt sum = a.plus(b);
        Pt doubledSum = sum.dbl();
        assertEquals(-1.686, doubledSum.x, 0.001);
        assertEquals(-1.884, doubledSum.y, 0.001);
    }
}
