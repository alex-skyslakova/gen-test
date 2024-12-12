import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class DigitalRootTest {

    @Test
    void testCalcDigitalRoot_singleDigit() {
        assertArrayEquals(new int[]{0, 5}, DigitalRoot.calcDigitalRoot("5", 10));
    }

    @Test
    void testCalcDigitalRoot_multipleDigits_oneIteration() {
        assertArrayEquals(new int[]{1, 6}, DigitalRoot.calcDigitalRoot("15", 10));
    }

    @Test
    void testCalcDigitalRoot_multipleDigits_multipleIterations() {
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("627615", 10));
        assertArrayEquals(new int[]{2, 6}, DigitalRoot.calcDigitalRoot("39390", 10));
        assertArrayEquals(new int[]{2, 3}, DigitalRoot.calcDigitalRoot("588225", 10));
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("393900588225", 10));
    }


    @Test
    void testCalcDigitalRoot_zero() {
        assertArrayEquals(new int[]{0, 0}, DigitalRoot.calcDigitalRoot("0", 10));
    }

    @Test
    void testCalcDigitalRoot_negativeNumber() {
        assertArrayEquals(new int[]{1, 7}, DigitalRoot.calcDigitalRoot("-25", 10));
    }

    @Test
    void testCalcDigitalRoot_differentBase() {
        assertArrayEquals(new int[]{1, 5}, DigitalRoot.calcDigitalRoot("12", 8)); // 12 in base 8 is 10 in base 10, digital root is 1+0=1
        assertArrayEquals(new int[]{1, 1}, DigitalRoot.calcDigitalRoot("7", 2)); // 7 in base 10 is 111 in base 2, digital root is 3 in base 10 or 11 in base 2 which results with 1 in next iteration.

    }

    @Test
    void testCalcDigitalRoot_largeNumber() {
        assertArrayEquals(new int[]{2, 9}, DigitalRoot.calcDigitalRoot("12345678901234567890", 10));
    }


}
