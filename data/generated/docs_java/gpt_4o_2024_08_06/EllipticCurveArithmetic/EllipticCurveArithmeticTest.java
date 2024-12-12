import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PtTest {

    @Test
    void testZeroPoint() {
        Pt zero = Pt.zero();
        assertTrue(zero.isZero(), "Zero point should be identified as zero");
    }

    @Test
    void testFromY() {
        Pt point = Pt.fromY(1);
        assertEquals(1, point.y, "Y coordinate should be 1");
        assertEquals(Math.cbrt(1 * 1 - Pt.bCoeff), point.x, "X coordinate should be calculated correctly");
    }

    @Test
    void testNegation() {
        Pt point = new Pt(3, 4);
        Pt negated = point.neg();
        assertEquals(point.x, negated.x, "X coordinate should remain the same after negation");
        assertEquals(-point.y, negated.y, "Y coordinate should be negated");
    }

    @Test
    void testDoubling() {
        Pt point = new Pt(3, 4);
        Pt doubled = point.dbl();
        assertNotEquals(point, doubled, "Doubled point should not be the same as the original");
    }

    @Test
    void testAdditionWithZero() {
        Pt point = new Pt(3, 4);
        Pt zero = Pt.zero();
        Pt result = point.plus(zero);
        assertEquals(point.x, result.x, "Adding zero should not change the x coordinate");
        assertEquals(point.y, result.y, "Adding zero should not change the y coordinate");
    }

    @Test
    void testAddition() {
        Pt a = new Pt(3, 4);
        Pt b = new Pt(5, 6);
        Pt result = a.plus(b);
        assertNotNull(result, "Result of addition should not be null");
    }

    @Test
    void testAdditionOfSamePoint() {
        Pt a = new Pt(3, 4);
        Pt result = a.plus(a);
        assertNotNull(result, "Result of addition should not be null");
    }

    @Test
    void testMultiplication() {
        Pt point = new Pt(3, 4);
        Pt result = point.mult(3);
        assertNotNull(result, "Result of multiplication should not be null");
    }

    @Test
    void testMultiplicationByZero() {
        Pt point = new Pt(3, 4);
        Pt result = point.mult(0);
        assertTrue(result.isZero(), "Multiplying by zero should result in zero point");
    }

    @Test
    void testMultiplicationByOne() {
        Pt point = new Pt(3, 4);
        Pt result = point.mult(1);
        assertEquals(point.x, result.x, "Multiplying by one should not change the x coordinate");
        assertEquals(point.y, result.y, "Multiplying by one should not change the y coordinate");
    }

    @Test
    void testToString() {
        Pt point = new Pt(3, 4);
        assertEquals("(3.000,4.000)", point.toString(), "String representation should match expected format");
    }

    @Test
    void testToStringZero() {
        Pt zero = Pt.zero();
        assertEquals("Zero", zero.toString(), "String representation of zero should be 'Zero'");
    }
}
