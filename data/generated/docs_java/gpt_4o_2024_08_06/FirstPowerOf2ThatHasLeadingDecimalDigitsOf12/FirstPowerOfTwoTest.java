import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FirstPowerOfTwoTest {

    @Test
    public void testPFunction() {
        // Test cases based on the problem statement
        assertEquals(7, FirstPowerOfTwo.p(12, 1));
        assertEquals(80, FirstPowerOfTwo.p(12, 2));
        assertEquals(12710, FirstPowerOfTwo.p(123, 45));

        // Additional test cases
        // These are computationally intensive and may take a long time to run
        // Uncomment them if you want to verify the results
        // assertEquals(expectedValue, FirstPowerOfTwo.p(123, 12345));
        // assertEquals(expectedValue, FirstPowerOfTwo.p(123, 678910));
    }
}
