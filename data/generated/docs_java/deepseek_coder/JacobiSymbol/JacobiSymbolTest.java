import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JacobiSymbolTest {

    @Test
    public void testJacobiSymbol_ValidValues() {
        // Test cases with valid values
        assertEquals(1, JacobiSymbol.jacobiSymbol(1, 1));
        assertEquals(1, JacobiSymbol.jacobiSymbol(1, 3));
        assertEquals(-1, JacobiSymbol.jacobiSymbol(2, 3));
        assertEquals(1, JacobiSymbol.jacobiSymbol(3, 5));
        assertEquals(-1, JacobiSymbol.jacobiSymbol(5, 7));
        assertEquals(1, JacobiSymbol.jacobiSymbol(7, 11));
        assertEquals(-1, JacobiSymbol.jacobiSymbol(11, 13));
        assertEquals(1, JacobiSymbol.jacobiSymbol(13, 17));
        assertEquals(-1, JacobiSymbol.jacobiSymbol(17, 19));
        assertEquals(1, JacobiSymbol.jacobiSymbol(19, 23));
    }

    @Test
    public void testJacobiSymbol_InvalidValues() {
        // Test cases with invalid values (k < 0 or n % 2 == 0)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            JacobiSymbol.jacobiSymbol(-1, 3);
        });
        String expectedMessage = "Invalid value. k = -1, n = 3";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        exception = assertThrows(IllegalArgumentException.class, () -> {
            JacobiSymbol.jacobiSymbol(2, 4);
        });
        expectedMessage = "Invalid value. k = 2, n = 4";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testJacobiSymbol_EdgeCases() {
        // Test cases with edge values
        assertEquals(1, JacobiSymbol.jacobiSymbol(1, 1));
        assertEquals(0, JacobiSymbol.jacobiSymbol(0, 1));
        assertEquals(1, JacobiSymbol.jacobiSymbol(1, 3));
        assertEquals(0, JacobiSymbol.jacobiSymbol(3, 3));
        assertEquals(1, JacobiSymbol.jacobiSymbol(1, 5));
        assertEquals(0, JacobiSymbol.jacobiSymbol(5, 5));
    }

    @Test
    public void testJacobiSymbol_LargeValues() {
        // Test cases with large values
        assertEquals(1, JacobiSymbol.jacobiSymbol(12345, 12347));
        assertEquals(-1, JacobiSymbol.jacobiSymbol(54321, 54323));
        assertEquals(1, JacobiSymbol.jacobiSymbol(98765, 98767));
    }
}
