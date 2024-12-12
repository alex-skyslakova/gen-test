import org.junit.Test;
import static org.junit.Assert.*;

public class JacobiSymbolTest {

    @Test
    public void testJacobiSymbolBasicCases() {
        assertEquals(1, JacobiSymbol.jacobiSymbol(1, 3)); // (1 | 3) = 1
        assertEquals(1, JacobiSymbol.jacobiSymbol(2, 3)); // (2 | 3) = 1
        assertEquals(-1, JacobiSymbol.jacobiSymbol(3, 5)); // (3 | 5) = -1
        assertEquals(0, JacobiSymbol.jacobiSymbol(0, 7)); // (0 | 7) = 0
    }

    @Test
    public void testJacobiSymbolEvenN() {
        try {
            JacobiSymbol.jacobiSymbol(3, 4); // n is even
            fail("Expected IllegalArgumentException for even n");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid value. k = 3, n = 4", e.getMessage());
        }
    }

    @Test
    public void testJacobiSymbolNegativeK() {
        try {
            JacobiSymbol.jacobiSymbol(-3, 5); // k is negative
            fail("Expected IllegalArgumentException for negative k");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid value. k = -3, n = 5", e.getMessage());
        }
    }

    @Test
    public void testJacobiSymbolKGreaterThanN() {
        assertEquals(-1, JacobiSymbol.jacobiSymbol(7, 3)); // (7 | 3) = (1 | 3) = -1
        assertEquals(1, JacobiSymbol.jacobiSymbol(10, 7)); // (10 | 7) = (3 | 7) = 1
    }

    @Test
    public void testJacobiSymbolSpecialCases() {
        assertEquals(0, JacobiSymbol.jacobiSymbol(0, 9)); // (0 | 9) = 0
        assertEquals(1, JacobiSymbol.jacobiSymbol(1, 9)); // (1 | 9) = 1
        assertEquals(1, JacobiSymbol.jacobiSymbol(9, 9)); // (9 | 9) = 1
    }

    @Test
    public void testJacobiSymbolLargeNumbers() {
        assertEquals(1, JacobiSymbol.jacobiSymbol(1001, 9907)); // Large numbers
        assertEquals(-1, JacobiSymbol.jacobiSymbol(123456, 78901)); // Large numbers
    }
}
