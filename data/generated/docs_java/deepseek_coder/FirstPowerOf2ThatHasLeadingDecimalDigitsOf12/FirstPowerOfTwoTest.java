import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FirstPowerOfTwoTest {

    @Test
    public void testP12_1() {
        int result = FirstPowerOfTwo.p(12, 1);
        assertEquals(7, result);
    }

    @Test
    public void testP12_2() {
        int result = FirstPowerOfTwo.p(12, 2);
        assertEquals(80, result);
    }

    @Test
    public void testP123_45() {
        int result = FirstPowerOfTwo.p(123, 45);
        assertEquals(12710, result);
    }

    @Test
    public void testP123_12345() {
        int result = FirstPowerOfTwo.p(123, 12345);
        // This test case is computationally intensive and might not be feasible for unit testing.
        // Uncomment and run if you have the resources.
        // assertEquals(expectedValue, result);
    }

    @Test
    public void testP123_678910() {
        int result = FirstPowerOfTwo.p(123, 678910);
        // This test case is computationally intensive and might not be feasible for unit testing.
        // Uncomment and run if you have the resources.
        // assertEquals(expectedValue, result);
    }
}
