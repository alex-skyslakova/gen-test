import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DigitalRootTest {

    @Test
    void testCalcDigitalRootBase10() {
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("627615", 10));
        assertArrayEquals(new int[]{2, 6}, DigitalRoot.calcDigitalRoot("39390", 10));
        assertArrayEquals(new int[]{2, 3}, DigitalRoot.calcDigitalRoot("588225", 10));
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("393900588225", 10));
    }

    @Test
    void testCalcDigitalRootSingleDigit() {
        assertArrayEquals(new int[]{0, 5}, DigitalRoot.calcDigitalRoot("5", 10));
        assertArrayEquals(new int[]{0, 0}, DigitalRoot.calcDigitalRoot("0", 10));
    }

    @Test
    void testCalcDigitalRootNegativeNumber() {
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("-627615", 10));
    }

    @Test
    void testCalcDigitalRootBase16() {
        assertArrayEquals(new int[]{1, 1}, DigitalRoot.calcDigitalRoot("1A", 16)); // 1A in base 16 is 26 in base 10, digital root is 8
        assertArrayEquals(new int[]{1, 7}, DigitalRoot.calcDigitalRoot("FF", 16)); // FF in base 16 is 255 in base 10, digital root is 3
    }

    @Test
    void testCalcDigitalRootBase2() {
        assertArrayEquals(new int[]{1, 1}, DigitalRoot.calcDigitalRoot("1101", 2)); // 1101 in base 2 is 13 in base 10, digital root is 4
    }
}
