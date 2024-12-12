import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DigitalRootTest {

    @Test
    void testCalcDigitalRoot_Base10() {
        // Test cases for base 10
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("627615", 10));
        assertArrayEquals(new int[]{2, 6}, DigitalRoot.calcDigitalRoot("39390", 10));
        assertArrayEquals(new int[]{2, 3}, DigitalRoot.calcDigitalRoot("588225", 10));
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("393900588225", 10));
    }

    @Test
    void testCalcDigitalRoot_Base2() {
        // Test cases for base 2
        assertArrayEquals(new int[]{2, 1}, DigitalRoot.calcDigitalRoot("1101110111011101110111011101110111011101110111011101110111011101", 2));
        assertArrayEquals(new int[]{1, 1}, DigitalRoot.calcDigitalRoot("1111111111111111111111111111111111111111111111111111111111111111", 2));
    }

    @Test
    void testCalcDigitalRoot_Base16() {
        // Test cases for base 16
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("9876F", 16));
        assertArrayEquals(new int[]{2, 6}, DigitalRoot.calcDigitalRoot("9A5F", 16));
        assertArrayEquals(new int[]{2, 3}, DigitalRoot.calcDigitalRoot("8F2E1", 16));
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("5A3B2C1D0E", 16));
    }

    @Test
    void testCalcDigitalRoot_NegativeNumber() {
        // Test case for negative number (should be treated as positive)
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("-627615", 10));
    }

    @Test
    void testCalcDigitalRoot_SingleDigit() {
        // Test case for single digit number
        assertArrayEquals(new int[]{0, 7}, DigitalRoot.calcDigitalRoot("7", 10));
        assertArrayEquals(new int[]{0, 1}, DigitalRoot.calcDigitalRoot("1", 2));
        assertArrayEquals(new int[]{0, 15}, DigitalRoot.calcDigitalRoot("F", 16));
    }

    @Test
    void testCalcDigitalRoot_Zero() {
        // Test case for zero
        assertArrayEquals(new int[]{0, 0}, DigitalRoot.calcDigitalRoot("0", 10));
        assertArrayEquals(new int[]{0, 0}, DigitalRoot.calcDigitalRoot("0", 2));
        assertArrayEquals(new int[]{0, 0}, DigitalRoot.calcDigitalRoot("0", 16));
    }

    @Test
    void testCalcDigitalRoot_LargeNumber() {
        // Test case for a very large number
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999", 10));
    }
}
