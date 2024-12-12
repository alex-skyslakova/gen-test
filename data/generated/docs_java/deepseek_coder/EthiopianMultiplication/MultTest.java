import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MultTest {

    @Test
    public void testDoubleInt() {
        assertEquals(4, Mult.doubleInt(2));
        assertEquals(0, Mult.doubleInt(0));
        assertEquals(-4, Mult.doubleInt(-2));
        assertEquals(1024, Mult.doubleInt(512));
    }

    @Test
    public void testHalveInt() {
        assertEquals(2, Mult.halveInt(4));
        assertEquals(0, Mult.halveInt(0));
        assertEquals(-2, Mult.halveInt(-4));
        assertEquals(256, Mult.halveInt(512));
    }

    @Test
    public void testIsEven() {
        assertTrue(Mult.isEven(4));
        assertFalse(Mult.isEven(3));
        assertTrue(Mult.isEven(0));
        assertFalse(Mult.isEven(-1));
        assertTrue(Mult.isEven(-2));
    }

    @Test
    public void testEthiopianMultiplication() {
        // Test case 1: 17 * 34 = 578
        assertEquals(578, ethiopianMultiplication(17, 34));

        // Test case 2: 5 * 10 = 50
        assertEquals(50, ethiopianMultiplication(5, 10));

        // Test case 3: 0 * 10 = 0
        assertEquals(0, ethiopianMultiplication(0, 10));

        // Test case 4: 10 * 0 = 0
        assertEquals(0, ethiopianMultiplication(10, 0));

        // Test case 5: -5 * 10 = -50
        assertEquals(-50, ethiopianMultiplication(-5, 10));

        // Test case 6: 10 * -5 = -50
        assertEquals(-50, ethiopianMultiplication(10, -5));

        // Test case 7: -5 * -10 = 50
        assertEquals(50, ethiopianMultiplication(-5, -10));
    }

    private int ethiopianMultiplication(int first, int second) {
        if (first < 0) {
            first = -first;
            second = -second;
        }

        int sum = Mult.isEven(first) ? 0 : second;
        do {
            first = Mult.halveInt(first);
            second = Mult.doubleInt(second);
            if (!Mult.isEven(first)) {
                sum += second;
            }
        } while (first > 1);

        return sum;
    }
}
