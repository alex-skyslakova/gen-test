import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MultTest {

    @Test
    public void testDoubleInt() {
        assertEquals(2, Mult.doubleInt(1));
        assertEquals(4, Mult.doubleInt(2));
        assertEquals(0, Mult.doubleInt(0));
        assertEquals(-4, Mult.doubleInt(-2));
    }

    @Test
    public void testHalveInt() {
        assertEquals(0, Mult.halveInt(1));
        assertEquals(1, Mult.halveInt(2));
        assertEquals(0, Mult.halveInt(0));
        assertEquals(-1, Mult.halveInt(-2));
    }

    @Test
    public void testIsEven() {
        assertEquals(true, Mult.isEven(2));
        assertEquals(false, Mult.isEven(1));
        assertEquals(true, Mult.isEven(0));
        assertEquals(true, Mult.isEven(-2));
        assertEquals(false, Mult.isEven(-1));
    }

    @Test
    public void testEthiopianMultiplication() {
        assertEquals(578, performEthiopianMultiplication(17, 34));
        assertEquals(0, performEthiopianMultiplication(0, 34));
        assertEquals(-578, performEthiopianMultiplication(-17, 34));
        assertEquals(578, performEthiopianMultiplication(-17, -34));
        assertEquals(0, performEthiopianMultiplication(17, 0));
    }

    private int performEthiopianMultiplication(int first, int second) {
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
